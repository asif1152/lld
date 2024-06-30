package org.example.managers;

import org.example.exceptions.NoFreeSlot;
import org.example.exceptions.SlotAlreadyFree;
import org.example.models.Slot;

import java.util.ArrayList;
import java.util.List;

public class LinearSlotService implements  ISlotService{

    private List<Slot> slotList;

    public LinearSlotService(List<Slot> slotList){
        this.slotList = slotList;
    }

    public LinearSlotService(){
        this.slotList = new ArrayList<>();
    }

    @Override
    public synchronized Slot getFreeSlot() throws NoFreeSlot {

        for(Slot slot: this.slotList){
            if(slot.getIsFree())
                return slot;
        }
        throw new NoFreeSlot();
    }

    @Override
    public synchronized void blockSlot(Slot slot) throws NoFreeSlot {
        if(!slot.getIsFree())
            throw new NoFreeSlot();

        slot.blockSlot();
    }

    @Override
    public synchronized void unblockSlot(Slot slot) throws SlotAlreadyFree {
        if(slot.getIsFree())
            throw new SlotAlreadyFree();
        slot.unblockSlot();
    }

    @Override
    public void addSlot(Slot slot) {
        this.slotList.add(slot);
    }

    @Override
    public void removeSlot(Slot slot) {
        this.slotList.remove(slot);
    }
}
