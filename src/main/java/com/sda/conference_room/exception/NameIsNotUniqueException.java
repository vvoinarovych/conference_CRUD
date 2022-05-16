package com.sda.conference_room.exception;

public class NameIsNotUniqueException extends RuntimeException{
    public NameIsNotUniqueException(final String message) {
        super(message);
    }
}
