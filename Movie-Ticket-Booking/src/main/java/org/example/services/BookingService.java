package org.example.services;

import org.example.dao.*;
import org.example.exceptions.SeatTemporaryUnavailableException;

import java.util.*;

public class BookingService {

    // TODO: take a lock on bookingMap -any read/write must be serialized otherwise we can assign same seat to different
    // TODO: users

    private Map<String, Booking> bookingMap;
    private SeatLocker seatLocker;
    public BookingService(SeatLocker inMemorySeatLocker){
        this.bookingMap = new HashMap<>();
        this.seatLocker = inMemorySeatLocker;

    }

    public Booking createBooking(String user, Show show, List<Seat> seatList){

        if(isSeatsAlreadyBooked(show, seatList)){
            throw new SeatTemporaryUnavailableException();
        }
        seatLocker.lockSeats(show, seatList, user);
        Booking booking = new Booking(UUID.randomUUID().toString(), user, show, seatList);
        this.bookingMap.put(booking.getId(), booking);
        return booking;
    }

    private boolean isSeatsAlreadyBooked(Show show, List<Seat> seatList){
        List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat bookedSeat: bookedSeats){
            if(seatList.contains(bookedSeat))
                return true;
        }
        return false;
    }


    public List<Booking> getAllBookings(Show show){

        List<Booking> allBookings = new ArrayList<>();

        for(Booking booking: bookingMap.values()){
            if(booking.getShow().equals(show)){
                allBookings.add(booking);
            }
        }
        return allBookings;

    }

    public List<Seat> getBookedSeats(Show show){
        List<Booking> allBookings = getAllBookings(show);
        List<Seat> bookedSeats = new ArrayList<>();
        for(Booking booking: allBookings){
            if(booking.isBookingConfirmed()){
                bookedSeats.addAll(booking.getSeatBooked());
            }
        }
        return bookedSeats;
    }

    public void confirmBooking(Booking booking){

        for(Seat seat: booking.getSeatBooked()){
            if(!seatLocker.validLock(booking.getShow(), seat, booking.getUser())){
                throw new SeatTemporaryUnavailableException();
            }
        }
        booking.confirmBooking();
    }





}
