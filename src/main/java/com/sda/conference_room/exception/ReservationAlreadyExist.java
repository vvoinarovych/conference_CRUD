package com.sda.conference_room.exception;

public class ReservationAlreadyExist extends RuntimeException{
    public  ReservationAlreadyExist(final String message) {
        super(message);
    }
}
