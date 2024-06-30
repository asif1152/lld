package org.example.managers;

import org.example.exceptions.NoFreeSlot;
import org.example.exceptions.SlotAlreadyFree;
import org.example.models.Slot;

public interface ISlotService {

    Slot getFreeSlot() throws NoFreeSlot;
    void blockSlot(Slot slot) throws NoFreeSlot;
    void unblockSlot(Slot slot) throws SlotAlreadyFree;

    void addSlot(Slot slot);

    void removeSlot(Slot slot);
}
