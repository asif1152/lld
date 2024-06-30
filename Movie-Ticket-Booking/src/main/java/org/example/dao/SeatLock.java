package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class SeatLock {

    private Show show;
    private final Seat seat;
    private String user;
    private Date lockTime;
    private Integer lockDurationInSecs;

    public boolean isLockExpired(){
        return this.lockTime.toInstant().plusSeconds(this.lockDurationInSecs).isBefore(new Date().toInstant());
    }
}
