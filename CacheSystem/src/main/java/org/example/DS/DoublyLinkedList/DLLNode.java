package org.example.DS.DoublyLinkedList;

@lombok.Getter
public class DLLNode<K> {
    K key;
    DLLNode<K> next, prev;

    public DLLNode(K key){
        this.key = key;
        this.next = this.prev = null;
    }
}
