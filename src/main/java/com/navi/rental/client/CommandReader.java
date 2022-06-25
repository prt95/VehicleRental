package com.navi.rental.client;

import com.navi.rental.commands.Command;
import com.navi.rental.exceptions.CommandExecutionException;
import com.navi.rental.execution.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public abstract class CommandReader {

    private BufferedReader inputReader;
    private CommandFactory commandFactory;

    public CommandReader(BufferedReader inputReader, CommandFactory commandFactory) {
        this.inputReader = inputReader;
        this.commandFactory = commandFactory;
    }

    public void handleInput() throws IOException, CommandExecutionException {
        try {
            while (true) {
                String inputLine = this.inputReader.readLine();
                if (inputLine == null) {
                    break;
                }

                inputLine = inputLine.trim();
                if (inputLine.isEmpty()) {
                    continue;
                }

                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }

                processInputLine(inputLine);
            }
        } finally {
            inputReader.close();
        }
    }

    private void processInputLine(String inputLine) {
        String[] inputChunks = inputLine.split(" ");
        String commandName = inputChunks[0];
        String[] params = Arrays.copyOfRange(inputChunks, 1, inputChunks.length);
        try {
            Command command = commandFactory.getCommand(commandName);
            command.process(params);
        } catch (CommandExecutionException ex) {
            System.out.println("Command execution failed: " + ex.getMessage());
        }

    }
}