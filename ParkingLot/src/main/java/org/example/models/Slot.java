package org.example.models;

@lombok.Getter
public class Slot {
    private Integer slotNo;
    private Boolean isFree;
    public Slot(int slotNo){
        this.slotNo = slotNo;
        this.isFree = true;
    }
    public void blockSlot(){
        this.isFree = false;
    }

    public void unblockSlot(){
        this.isFree = true;
    }

}
