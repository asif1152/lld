package org.example.managers;

import org.example.exceptions.NoFreeSlot;
import org.example.exceptions.SlotAlreadyFree;
import org.example.models.Car;
import org.example.models.Slot;
import org.example.models.Ticket;
import org.example.models.Vehicle;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingLotService {

    private VehicleManager vehicleManager;
    private SlotManager slotManager;
    private TicketManager ticketManager;

    private ISlotService slotService;

    public ParkingLotService(){
        this.vehicleManager = new VehicleManager();
        this.slotManager = new SlotManager();
        this.ticketManager = new TicketManager();
        this.slotService = new LinearSlotService();
    }

    public void createSlots(int n){
        for(int i=1;i<=n;++i){
            Slot slot = new Slot(i);
            this.slotManager.addSlot(slot);
            this.slotService.addSlot(slot);
        }
        System.out.printf("Created a parking lot with %d slots\n", n);
    }

    public void park(String color, String regNo) {

        try{
            Vehicle vehicle = new Car(color, regNo);

            Slot slot = this.slotService.getFreeSlot();
            slotService.blockSlot(slot);
            Ticket ticket = new Ticket(slot, vehicle);
            this.vehicleManager.addVehicle(vehicle);
            ticketManager.addTicket(ticket);

            System.out.printf("Allocated slot number: %d\n", slot.getSlotNo());

        }catch (NoFreeSlot ex){
           System.out.println("Sorry, parking lot is full");
        }
    }

    public void leave(int slotNo){
        Ticket ticket = this.ticketManager.getTicketForSlotNo(slotNo);
        if(Objects.isNull(ticket)){
            System.out.println("No vehicle present at this slot");
            return;
        }
        try{
            this.slotService.unblockSlot(ticket.getSlot());
        }
        catch (SlotAlreadyFree ex){
            System.out.println("No vehicle present at this slot");
            return;
        }
        this.vehicleManager.removeVehicle(ticket.getVehicle());
        this.ticketManager.removeTicket(ticket);

        System.out.printf("Slot number %d is free\n", ticket.getSlot().getSlotNo());
    }

    public void  getCurrentStatus(){
        System.out.println("Slot No. Registration No Colour");
        for(Ticket ticket:this.ticketManager.getAllTickets()){
            System.out.printf("%d %s %s\n",ticket.getSlot().getSlotNo(), ticket.getVehicle().getRegNo(),
                    ticket.getVehicle().getColor());

        }
    }

    public void getRegNoWithColor(String color){
        if(Objects.isNull(this.vehicleManager.getColorVehicles(color)))
        {
            System.out.println("Not found");
            return;
        }
        int i=0;
        for(Vehicle vehicle: this.vehicleManager.getColorVehicles(color)){
            ++i;
            System.out.printf("%s",vehicle.getRegNo());
            if(i!=this.vehicleManager.getColorVehicles(color).size())
                System.out.print(", ");
        }
        System.out.println();
    }

    public void getSlotNoWithColor(String color){
        if(Objects.isNull(this.ticketManager.getTicketsForColor(color)))
        {
            System.out.println("Not found");
            return;
        }
        int i=0;
        for(Ticket ticket: this.ticketManager.getTicketsForColor(color)){
            ++i;
            System.out.printf("%d",ticket.getSlot().getSlotNo());
            if(i!=this.ticketManager.getTicketsForColor(color).size())
                System.out.print(", ");
        }
        System.out.println();
    }

    public void getSlotNoForReg(String regNo){
        Ticket ticket = this.ticketManager.getTicketReg(regNo);
        if(Objects.isNull(ticket)){
            System.out.println("Not found");
            return;
        }
        System.out.printf("%d\n", ticket.getSlot().getSlotNo());
    }
}
