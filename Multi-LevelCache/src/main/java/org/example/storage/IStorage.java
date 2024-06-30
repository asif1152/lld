package org.example.storage;

public interface IStorage<K, V> {

    V get(K key);

    void put(K key, V Val);

    void remove(K key);

    double getUsage();

}
