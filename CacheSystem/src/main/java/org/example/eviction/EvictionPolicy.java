package org.example.eviction;

import org.example.DS.DoublyLinkedList.DLLNode;

public interface EvictionPolicy<K> {

    DLLNode<K> evictKey();

    void keyAccessed(K Key);

}
