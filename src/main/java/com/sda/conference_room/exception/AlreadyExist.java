package com.sda.conference_room.exception;

public class AlreadyExist extends RuntimeException{
    public AlreadyExist(final String message) {
        super(message);
    }
}
