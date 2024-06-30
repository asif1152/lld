package org.example.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.eviction.IEvictionPolicy;
import org.example.eviction.LRUEvictionPolicy;
import org.example.exceptions.LevelDataCountMismatchException;
import org.example.models.LevelCacheData;
import org.example.models.LevelReadResponse;
import org.example.models.LevelWriteResponse;
import org.example.storage.HashMapStorage;
import org.example.storage.IStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class MultiLevelCache<K,V> {

    private final int noLevels;

    private List<LevelCacheData> levelCacheDataList;
    private List<ILevelCache<K,V>> levelCacheList;

    private List<Integer> readTimeList, writeTimeList;
    private int limit;
    public MultiLevelCache(int noLevels, List<LevelCacheData> levelCacheDataList, int statsLimit){
        this.noLevels = noLevels;
        if(levelCacheDataList.size() != noLevels){
            throw new LevelDataCountMismatchException();
        }
        this.levelCacheList = new ArrayList<>();
        for(int i=0;i<noLevels;++i){
            IEvictionPolicy<K> evictionPolicy = new LRUEvictionPolicy<>();
            IStorage<K,V> storage = new HashMapStorage<>(levelCacheDataList.get(i).getCapacity());
            ILevelCache<K,V> levelCache = new LevelCache<>(evictionPolicy, storage, levelCacheDataList.get(i));
            this.levelCacheList.add(levelCache);
        }
        readTimeList = new ArrayList<>();
        writeTimeList = new ArrayList<>();
        this.limit = statsLimit;
    }

    private void addList(List<Integer> tmpList, int time){
        if(tmpList.size()>=this.limit){
            tmpList.remove(0);
        }
        tmpList.add(time);
    }
    private double getAvg(List<Integer> tmpList){
        if(tmpList.size()==0)
            return 0;
        int totalTime =0;
        for(int time: tmpList){
            totalTime+=time;
        }
        return totalTime/(double)tmpList.size();
    }

    public LevelReadResponse<V> get(K key){
        int totalReadTime =0;
        LevelReadResponse<V> levelReadResponse = null;

        for(int i=0;i<noLevels;++i){
            levelReadResponse = this.levelCacheList.get(i).get(key);
            totalReadTime += this.levelCacheList.get(i).getReadTime();
            if(levelReadResponse==null){
                continue;
            }else{
                // going to write to all cache behind
                for(int j=0;j<i;++j){
                    totalReadTime += this.levelCacheList.get(j).getWriteTime();
                    this.levelCacheList.get(j).put(key, (V) this.levelCacheList.get(i).get(key).getVal());
                }
                break;
            }
        }
        // key not found
        if(levelReadResponse == null){
            levelReadResponse = new LevelReadResponse<>(totalReadTime, null);
        }else{
            levelReadResponse = new LevelReadResponse<>(totalReadTime, levelReadResponse.getVal());
        }
        addList(this.readTimeList, totalReadTime);
        if(levelReadResponse.getVal() == null){
            System.out.printf("Key not found in cache, Read time taken for key is: %d\n", totalReadTime);
        }else{
            System.out.printf("Key found in cache, Read time taken for key is: %d, val is: %s\n",
                    totalReadTime, levelReadResponse.getVal());
        }
        return levelReadResponse;
    }

    public LevelWriteResponse put(K key, V val){
        int totalWriteTime =0;
        for(int i=0;i<noLevels;++i){
            LevelReadResponse<V> levelReadResponseTmp  = this.levelCacheList.get(i).get(key);
            totalWriteTime += this.levelCacheList.get(i).getReadTime();
            if(Objects.isNull(levelReadResponseTmp)){
                this.levelCacheList.get(i).put(key, val);
                totalWriteTime += this.levelCacheList.get(i).getWriteTime();
            }else if(levelReadResponseTmp.getVal() != val){
                this.levelCacheList.get(i).put(key, val);
                totalWriteTime += this.levelCacheList.get(i).getWriteTime();
            }else { // == val
                break;
            }
        }
        addList(this.writeTimeList, totalWriteTime);
        System.out.printf("Write time taken is: %d\n", totalWriteTime);
        return new LevelWriteResponse(totalWriteTime);
    }

    public void printStats(){

        for(int i=0;i<noLevels;++i) {
            double levelUsage = this.levelCacheList.get(i).getUsage();
            System.out.printf("Usage for Level Cache: %d is: %f\n", i + 1, levelUsage);
        }
        double avgReadTime = this.getAvg(this.readTimeList);
        double avgWriteTime = this.getAvg(this.writeTimeList);
        System.out.printf("Avg Reading time for last 10 transactions is: %f\n", avgReadTime);
        System.out.printf("Avg Writing time for last 10 transactions is: %f\n", avgWriteTime);

    }
}













