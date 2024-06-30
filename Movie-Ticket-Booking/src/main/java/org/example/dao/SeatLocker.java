package org.example.dao;

import java.util.List;

public interface SeatLocker {

    void lockSeats(Show show, List<Seat> seatList, String user);

    void unlockSeats(Show show, List<Seat> seatList, String user);

    List<Seat> getLockedSeats(Show show);

    boolean validLock(Show show, Seat seat, String user);


}
