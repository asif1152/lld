package org.example.cache;

import org.example.models.LevelReadResponse;
import org.example.models.LevelWriteResponse;

public interface ILevelCache<K,V> {

    LevelReadResponse<V> get(K key);
    LevelWriteResponse put(K key, V val);

    double getUsage();

    int getReadTime();

    int getWriteTime();

}
