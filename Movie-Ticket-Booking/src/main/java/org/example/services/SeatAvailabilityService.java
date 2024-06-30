package org.example.services;

import org.example.dao.InMemorySeatLocker;
import org.example.dao.Seat;
import org.example.dao.SeatLocker;
import org.example.dao.Show;

import java.util.List;

public class SeatAvailabilityService {

    private final BookingService bookingService;
    private SeatLocker inMemorySeatLocker;

    public SeatAvailabilityService(BookingService bookingService, SeatLocker inMemorySeatLocker){
        this.bookingService = bookingService;
        this.inMemorySeatLocker = inMemorySeatLocker;
    }

    public List<Seat> getAvailableSeats(Show show){
        List<Seat> totalSeats = show.getScreen().getSeatList();
        List<Seat> bookedSeats =  bookingService.getBookedSeats(show);
        List<Seat> lockedSeats = inMemorySeatLocker.getLockedSeats(show);
        totalSeats.removeAll(bookedSeats);
        totalSeats.removeAll(lockedSeats);
        return totalSeats;
    }



}
