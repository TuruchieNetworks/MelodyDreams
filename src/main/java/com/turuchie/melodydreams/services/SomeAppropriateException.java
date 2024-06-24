package com.turuchie.melodydreams.services;

public class SomeAppropriateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SomeAppropriateException(String message) {
        super(message);
    }

    public SomeAppropriateException(String message, Throwable cause) {
        super(message, cause);
    }
}
