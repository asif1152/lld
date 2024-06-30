package org.example.Cache;

import org.example.DS.DoublyLinkedList.DLLNode;
import org.example.eviction.EvictionPolicy;
import org.example.exceptions.CacheStorageFullException;
import org.example.exceptions.KeyNotFoundException;
import org.example.storage.ICacheStorage;

public class Cache<K, V> implements ICache<K,V> {

    private final ICacheStorage<K, V> cacheStorage;
    private final EvictionPolicy<K> evictionPolicy;
    public Cache(ICacheStorage<K, V> cacheStorage, EvictionPolicy<K> evictionPolicy){
        this.cacheStorage = cacheStorage;
        this.evictionPolicy = evictionPolicy;
    }

    @Override
    public V get(K key) {
        try{
            this.evictionPolicy.keyAccessed(key);
            return this.cacheStorage.get(key);
        }catch (KeyNotFoundException ex){
            System.out.printf("key does not exist in cache for: %s\n", key);
        }
        return null;
    }

    @Override
    public void put(K key, V val) {
        try {
            this.cacheStorage.put(key, val);
            this.evictionPolicy.keyAccessed(key);
            System.out.println("key successfully cached");
        }catch (CacheStorageFullException ex){
            DLLNode<K> dllNode= this.evictionPolicy.evictKey();
            System.out.printf("key evicted is: %s\n", dllNode.getKey());
            this.cacheStorage.remove(dllNode.getKey());
            put(key,val);
        }
    }
}
