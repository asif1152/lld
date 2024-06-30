package org.example.storage;

import org.example.exceptions.CacheFullException;
import org.example.exceptions.KeyNotFundInCacheException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<K,V> implements IStorage<K,V> {

    private Map<K, V> dataMap;
    private int capacity;

    public HashMapStorage(int capacity){
        this.dataMap = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        if(!this.dataMap.containsKey(key))
            throw new KeyNotFundInCacheException();
        return this.dataMap.get(key);
    }

    @Override
    public void put(K key, V val) {
        if(this.isFull())
            throw new CacheFullException();
        this.dataMap.put(key, val);
    }

    @Override
    public void remove(K key) {
        if(!this.dataMap.containsKey(key))
            throw new KeyNotFundInCacheException();
        this.dataMap.remove(key);
    }

    @Override
    public double getUsage() {
        return (double) this.dataMap.size() /(double)this.capacity;

    }

    private boolean isFull(){
        return this.dataMap.size() >= this.capacity;
    }
}
