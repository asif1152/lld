package org.example.storage;

import org.example.exceptions.CacheStorageFullException;
import org.example.exceptions.KeyNotFoundException;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class InMemoryMapStorage<K,V> implements ICacheStorage<K,V>{

    private Map<K,V> storageMap;
    private Integer capacity;
    public InMemoryMapStorage(Integer capacity){
        this.storageMap = new HashMap<>();
        this.capacity = capacity;
    }
    @Override
    public void put(K key, V val) throws CacheStorageFullException {
        if(isFull()){
            throw new CacheStorageFullException();
        }
        this.storageMap.put(key, val);
    }

    @Override
    public V get(K key) throws KeyNotFoundException {
        if(!this.storageMap.containsKey(key)){
            throw new KeyNotFoundException();
        }
        return this.storageMap.get(key);
    }

    @Override
    public void remove(K key) {
        if(!this.storageMap.containsKey(key)) throw new KeyNotFoundException();
        this.storageMap.remove(key);
    }

    private boolean isFull(){
        return this.storageMap.size() >= this.capacity;
    }
}
