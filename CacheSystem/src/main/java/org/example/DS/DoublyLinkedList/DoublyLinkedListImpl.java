package org.example.DS.DoublyLinkedList;
@lombok.Getter
public class DoublyLinkedListImpl<K> {

    private DLLNode<K> head;
    private DLLNode<K> last;

    public DoublyLinkedListImpl(){
        this.head = null;
        this.last = null;
    }

    public DLLNode<K> addNewNode(K val){
        DLLNode<K> dllNode = new DLLNode<>(val);
        if(this.head==null){
            this.head = this.last = dllNode;
        }else{
            dllNode.next = this.head;
            this.head.prev = dllNode;
            this.head = dllNode;
        }
        return dllNode;
    }

    public void addNodeAtHead(DLLNode<K> dllNode){
        if(dllNode == null)
            return;
        if(this.head == null){
            dllNode.prev= dllNode.next = null;
            this.head = this.last = dllNode;
            return;
        }
        dllNode.next = this.head;
        dllNode.prev = null;
        this.head.prev = dllNode;
        this.head = dllNode;
    }

    public void removeNode(DLLNode<K> dllNode){
        // single node case
        if(this.head == this.last){
            this.head = this.last = null;
            return;
        }
        if(dllNode == this.last){
            this.last = dllNode.prev;
            dllNode.prev.next = null;
        }else if(dllNode == this.head){
            this.head = dllNode.next;
            dllNode.next.prev = null;
        }else{
            DLLNode<K> prevNode = dllNode.prev;
            prevNode.next = dllNode.next;
            dllNode.next.prev = prevNode;
        }
    }

    public boolean isDLLEmpty(){
        return this.head == null;
    }
}
