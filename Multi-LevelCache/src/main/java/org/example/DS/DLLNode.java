package org.example.DS;

@lombok.Getter
public class DLLNode<K> {

    K key;
    DLLNode<K> next, prev;

    public DLLNode(K key){
        this.key = key;
        this.prev = this.next = null;
    }

}
