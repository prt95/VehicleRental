package com.navi.rental.exceptions;

public class BranchDoesNotExistException extends CommandExecutionException {
    public BranchDoesNotExistException(String message) {
        super(message);
    }
}
