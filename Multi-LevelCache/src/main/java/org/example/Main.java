package org.example;

import org.example.cache.MultiLevelCache;
import org.example.models.LevelCacheData;
import org.example.models.LevelWriteResponse;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int noLevels = 3;
        List<LevelCacheData> levelCacheDataList = new ArrayList<>();
        levelCacheDataList.add(new LevelCacheData(1, 2, 2));
        levelCacheDataList.add(new LevelCacheData(2, 3, 3));
        levelCacheDataList.add(new LevelCacheData(3, 5, 4));

        MultiLevelCache<String, String> multiLevelCache = new MultiLevelCache<>(3, levelCacheDataList,
                3);

        multiLevelCache.put("name", "Asif Khan");
        multiLevelCache.put("addr", "Banaglore");
        multiLevelCache.put("age", "29");

        multiLevelCache.printStats();

        multiLevelCache.get("name");
        multiLevelCache.get("addr");
        multiLevelCache.get("age");

        multiLevelCache.printStats();

        multiLevelCache.put("hobby", "coding");
        multiLevelCache.get("addr");
        multiLevelCache.get("name");

        multiLevelCache.printStats();





    }
}