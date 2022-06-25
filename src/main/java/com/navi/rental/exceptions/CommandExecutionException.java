package com.navi.rental.exceptions;

public class CommandExecutionException extends RuntimeException {
    public CommandExecutionException(String message) {
        super(message);
    }

    public CommandExecutionException(Throwable th) {
        super(th);
    }

    public CommandExecutionException(String message, Throwable th) {
        super(message, th);
    }
}
