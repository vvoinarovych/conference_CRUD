package com.sda.conference_room.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(final String message) {
        super(message);
    }
}
