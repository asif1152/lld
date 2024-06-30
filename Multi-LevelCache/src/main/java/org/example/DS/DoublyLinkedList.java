package org.example.DS;
@lombok.Getter
public class DoublyLinkedList<K> {

    private DLLNode<K> head, last;

    public DoublyLinkedList(){
        this.head = this.last = null;
    }

    public DLLNode<K> addEleAtHead(K key){
        DLLNode<K> dllNode = new DLLNode<>(key);
        return this.addNodeAtHead(dllNode);
    }

    public DLLNode<K> addNodeAtHead(DLLNode<K> dllNode){
        if(this.head==null){
            dllNode.next = dllNode.prev = null;
            this.head= this.last = dllNode;
            return dllNode;
        }
        dllNode.next = this.head;
        this.head.prev = dllNode;
        this.head = dllNode;
        return dllNode;
    }

    public void remove(DLLNode<K> dllNode){

        if(dllNode == null)
            return;

        if(this.head == this.last){
            this.head = this.last = null;
            return;
        }

        if(this.head == dllNode){
            this.head = dllNode.next;
            dllNode.next.prev = null;
        }else if(this.last == dllNode){
            this.last = dllNode.prev;
            this.last.next = null;
        }else{
            dllNode.prev.next = dllNode.next;
            dllNode.next.prev = dllNode.prev;
        }
    }

}
