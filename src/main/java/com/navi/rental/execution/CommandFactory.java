package com.navi.rental.execution;

import com.navi.rental.commands.AddBranchCommand;
import com.navi.rental.commands.AddVehicleCommand;
import com.navi.rental.commands.BookCommand;
import com.navi.rental.commands.Command;
import com.navi.rental.commands.DisplayVehicleCommand;
import com.navi.rental.exceptions.CommandNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private Map<String, Command> commands;

    private CommandFactory() {
        commands = new HashMap<>();
    }

    public static CommandFactory initialize() {
        final CommandFactory cf = new CommandFactory();
        cf.addCommand("add_branch", new AddBranchCommand());
        cf.addCommand("add_vehicle", new AddVehicleCommand());
        cf.addCommand("book", new BookCommand());
        cf.addCommand("display_vehicles", new DisplayVehicleCommand());
        return cf;
    }

    public Command getCommand(String name) throws CommandNotFoundException {
        name = name.toLowerCase();
        if (!commands.containsKey(name)) {
            throw new CommandNotFoundException(name);
        }
        return commands.get(name);
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }


    public void listCommandHelp() {
        commands
                .keySet()
                .stream()
                .map(command -> commands.get(command).getHelpText())
                .forEach(System.out::println);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
