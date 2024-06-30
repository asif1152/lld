package org.example.dao;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Screen {

    private final String id;

    private final Theatre theatre;

    private List<Seat> seatList;

    public Screen(String id, Theatre theatre){
        this.id = id;
        this.theatre = theatre;
        this.seatList = new ArrayList<>();
    }

    public void addSeat(Seat seat){
        this.seatList.add(seat);
    }





}
