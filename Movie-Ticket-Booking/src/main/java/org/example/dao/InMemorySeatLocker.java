package org.example.dao;

import org.example.exceptions.SeatTemporaryUnavailableException;

import java.util.*;

public class InMemorySeatLocker implements SeatLocker{

    // TODO: any read and write to this locks map must be thread safe.
    private Map<Show, Map<Seat, SeatLock>> locks;
    private Integer lockTimeOut;

    public InMemorySeatLocker(Integer lockTimeOut){
        this.locks = new HashMap<>();
        this.lockTimeOut = lockTimeOut;
    }

    @Override
    public synchronized void lockSeats(Show show, List<Seat> seatList, String user) {
        for(Seat seat: seatList){
            if(isSeatLocked(show, seat)){
                throw new SeatTemporaryUnavailableException();
            }
        }

        for(Seat seat: seatList){
            lockSeat(show, seat, user);
        }

    }

    private void lockSeat(Show show, Seat seat, String user){

        SeatLock seatLock = new SeatLock(show, seat, user, new Date(), this.lockTimeOut);
        if(!this.locks.containsKey(show)){
            locks.put(show, new HashMap<>());
        }
        locks.get(show).put(seat, seatLock);

    }

    private boolean isSeatLocked(Show show, Seat seat){
        return this.locks.containsKey(show) && this.locks.get(show).containsKey(seat) &&
                !this.locks.get(show).get(seat).
                        isLockExpired();
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seatList, String user) {

        for(Seat seat: seatList){
            if(validLock(show, seat, user)){
                unlockSeat(show, seat);
            }
        }
    }

    public boolean validLock(Show show, Seat seat, String user){
        return locks.containsKey(show) && locks.get(show).containsKey(seat) &&
                locks.get(show).get(seat).getUser().equals(user);
    }

    public void unlockSeat(Show show, Seat seat){
        this.locks.get(show).remove(seat);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {

        List<Seat> availableSeats = new ArrayList<>();

        if(this.locks.containsKey(show)){
            for(Seat seat: locks.get(show).keySet()){
                if(isSeatLocked(show, seat)){
                    availableSeats.add(seat);
                }
            }
        }
        return availableSeats;
    }
}
