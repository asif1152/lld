package org.example.Cache;

public interface ICache<K,V> {

    V get(K key);

    void put(K key, V val);

}
