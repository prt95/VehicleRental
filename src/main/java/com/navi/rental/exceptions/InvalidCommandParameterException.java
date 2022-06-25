package com.navi.rental.exceptions;

public class InvalidCommandParameterException extends CommandExecutionException {
    public InvalidCommandParameterException(String message) {
        super(message);
    }
}
