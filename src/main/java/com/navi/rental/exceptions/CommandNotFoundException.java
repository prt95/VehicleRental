package com.navi.rental.exceptions;

public class CommandNotFoundException extends CommandExecutionException {

    private String name;

    public CommandNotFoundException(String name) {
        super("Command " + name + " not found");
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "Command " + name + " not found";
    }
}
