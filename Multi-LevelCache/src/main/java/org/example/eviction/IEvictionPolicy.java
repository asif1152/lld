package org.example.eviction;

public interface IEvictionPolicy<K> {

    void keyAccessed(K key);
    K evictKey();
}
