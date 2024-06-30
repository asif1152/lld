package org.example.eviction;

import org.example.DS.DLLNode;
import org.example.DS.DoublyLinkedList;
import org.example.exceptions.NoKeyExistInLRUException;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements IEvictionPolicy<K>  {

    private DoublyLinkedList<K> doublyLinkedList;
    private Map<K, DLLNode<K>> nodeMap;

    public LRUEvictionPolicy(){
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.nodeMap = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if(!nodeMap.containsKey(key)){
           DLLNode<K> dllNode = this.doublyLinkedList.addEleAtHead(key);
           this.nodeMap.put(key, dllNode);
        }else{
            this.doublyLinkedList.remove(this.nodeMap.get(key));
            this.doublyLinkedList.addNodeAtHead(this.nodeMap.get(key));
        }
    }

    @Override
    public K evictKey() {

        if(this.doublyLinkedList.getLast()==null)
            throw new NoKeyExistInLRUException();

        DLLNode<K> node = this.doublyLinkedList.getLast();

        this.doublyLinkedList.remove(node);
        this.nodeMap.remove(node.getKey());
        return node.getKey();
    }
}
