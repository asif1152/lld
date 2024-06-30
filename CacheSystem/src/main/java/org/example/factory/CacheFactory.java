package org.example.factory;

import org.example.Cache.Cache;
import org.example.Cache.ICache;
import org.example.eviction.LRUEvictionPolicy;
import org.example.exceptions.CacheNotImplemented;
import org.example.storage.InMemoryMapStorage;

public class CacheFactory<K,V> {

    public ICache<K,V> getCacheObj(CacheEnum cacheEnum){
        switch (cacheEnum){
            case InMemoryMapCache:
                return new Cache<K,V>(new InMemoryMapStorage<K,V>(3),
                    new LRUEvictionPolicy<K>());
            default:
                throw new CacheNotImplemented();
        }
    }
}
