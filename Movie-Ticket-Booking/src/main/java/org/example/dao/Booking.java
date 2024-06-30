package org.example.dao;

import lombok.Getter;

import java.util.List;

@Getter
public class Booking {

    private final String id;

    private final String user;

    private final Show show;

    private final List<Seat> seatBooked;

    private BookingStatus bookingStatus;



    public Booking(String id, String user, Show show, List<Seat> seatList){
        this.id = id;
        this.user = user;
        this.show= show;
        this.seatBooked = seatList;
        this.bookingStatus= BookingStatus.Pending;
    }

    public void confirmBooking(){
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public boolean isBookingConfirmed(){
        return this.bookingStatus == BookingStatus.Confirmed;
    }

}
