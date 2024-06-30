package org.example.managers;

import org.example.models.Slot;
import org.example.models.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketManager {

    private List<Ticket> ticketList;
    private Map<Integer, Ticket> slotTicketMap;
    private Map<String, List<Ticket>> colorVsTicketMap;
    private Map<String, Ticket> regVsTicketMap;


    public TicketManager(){
        this.ticketList = new ArrayList<>();
        this.colorVsTicketMap = new HashMap<>();
        this.regVsTicketMap = new HashMap<>();
        this.slotTicketMap = new HashMap<>();
    }

    public void addTicket(Ticket ticket){
        this.ticketList.add(ticket);
        this.slotTicketMap.put(ticket.getSlot().getSlotNo(), ticket);
        if(!this.colorVsTicketMap.containsKey(ticket.getVehicle().getColor()))
            this.colorVsTicketMap.put(ticket.getVehicle().getColor(), new ArrayList<>());
        this.colorVsTicketMap.get(ticket.getVehicle().getColor()).add(ticket);
        this.regVsTicketMap.put(ticket.getVehicle().getRegNo(), ticket);
    }

    public void removeTicket(Ticket ticket){
        this.ticketList.remove(ticket);
        this.slotTicketMap.remove(ticket.getSlot().getSlotNo());
        this.colorVsTicketMap.remove(ticket.getVehicle().getColor());
        this.regVsTicketMap.remove(ticket.getVehicle().getRegNo());
    }

    public Ticket getTicketReg(String regNo){
       return this.regVsTicketMap.get(regNo);
    }
    public List<Ticket> getTicketsForColor(String color){
        return this.colorVsTicketMap.get(color);
    }

    public Ticket getTicketForSlotNo(int slotNo){
        return this.slotTicketMap.get(slotNo);
    }

    public List<Ticket> getAllTickets(){
        return this.ticketList;
    }




}
