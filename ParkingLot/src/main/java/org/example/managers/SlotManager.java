package org.example.managers;

import org.example.exceptions.NoFreeSlot;
import org.example.exceptions.SlotAlreadyFree;
import org.example.models.Slot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotManager {

    private Map<Integer, Slot> slotHashMap;
    private List<Slot> slotList;

    public SlotManager(){
        this.slotList = new ArrayList<>();
        this.slotHashMap = new HashMap<>();
    }

    public void addSlot(Slot slot){
        this.slotList.add(slot);
        this.slotHashMap.put(slot.getSlotNo(), slot);
    }

    public Slot getSlot(int slotNo){
      return   this.slotHashMap.get(slotNo);
    }

    public List<Slot> getAllSlot(){
        return this.slotList;
    }
}
