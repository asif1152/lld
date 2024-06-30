package org.example.storage;

public interface ICacheStorage<K,V> {

    void put(K key, V val);
    V get(K key);
    void remove(K key);

}
