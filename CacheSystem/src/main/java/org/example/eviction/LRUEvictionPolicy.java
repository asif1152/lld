package org.example.eviction;

import org.example.DS.DoublyLinkedList.DLLNode;
import org.example.DS.DoublyLinkedList.DoublyLinkedListImpl;
import org.example.exceptions.KeyNotFoundException;
import org.example.exceptions.NoKeysExistException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K>{

    DoublyLinkedListImpl<K> doublyLinkedList;
    Map<K, DLLNode<K>> dllNodeMap;

    public LRUEvictionPolicy(){

        this.doublyLinkedList = new DoublyLinkedListImpl<>();
        this.dllNodeMap = new HashMap<>();
    }
    @Override
    public DLLNode<K> evictKey() {
        DLLNode<K> leastAccessedNode = this.doublyLinkedList.getLast();
        if(leastAccessedNode == null){
            throw new NoKeysExistException();
        }
        this.doublyLinkedList.removeNode(leastAccessedNode);
        this.dllNodeMap.remove(leastAccessedNode.getKey());
        return leastAccessedNode;
    }

    @Override
    public void keyAccessed(K key) {
        if(!this.dllNodeMap.containsKey(key)){
            DLLNode<K> dllNode = this.doublyLinkedList.addNewNode(key);
            this.dllNodeMap.put(key, dllNode);
        }else{
            this.doublyLinkedList.removeNode(this.dllNodeMap.get(key));
            this.doublyLinkedList.addNodeAtHead(this.dllNodeMap.get(key));
        }
    }
}
