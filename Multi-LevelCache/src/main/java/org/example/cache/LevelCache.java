package org.example.cache;

import org.example.eviction.IEvictionPolicy;
import org.example.exceptions.CacheFullException;
import org.example.exceptions.KeyNotFundInCacheException;
import org.example.models.LevelCacheData;
import org.example.models.LevelReadResponse;
import org.example.models.LevelWriteResponse;
import org.example.storage.IStorage;


@lombok.Getter
public class LevelCache<K,V> implements ILevelCache<K,V>{


    private LevelCacheData levelCacheData;
    private IStorage<K, V> storage;
    private IEvictionPolicy<K> evictionPolicy;

    public LevelCache(IEvictionPolicy<K> evictionPolicy, IStorage<K, V> storage, LevelCacheData levelCacheData){
        this.evictionPolicy = evictionPolicy;
        this.levelCacheData = levelCacheData;
        this.storage = storage;
    }


    @Override
    public LevelReadResponse<V> get(K key) {
        try{
            V val= this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return new LevelReadResponse<V>(this.levelCacheData.getReadTime(), val);
        }catch (KeyNotFundInCacheException ex){
            return null;
        }
    }

    @Override
    public LevelWriteResponse put(K key, V val) {
        try{
            this.storage.put(key, val);
            this.evictionPolicy.keyAccessed(key);
            return new LevelWriteResponse(levelCacheData.getWriteTime());
        }catch (CacheFullException ex){
            K leastUsedKey = this.evictionPolicy.evictKey();
            this.storage.remove(leastUsedKey);
            put(key, val);
        }
        return new LevelWriteResponse(levelCacheData.getWriteTime());
    }

    @Override
    public double getUsage() {
        return this.storage.getUsage();
    }

    @Override
    public int getReadTime() {
        return this.levelCacheData.getReadTime();
    }

    @Override
    public int getWriteTime() {
        return this.levelCacheData.getWriteTime();
    }
}
