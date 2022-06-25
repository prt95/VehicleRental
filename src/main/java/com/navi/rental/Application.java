package com.navi.rental;

import com.navi.rental.client.CommandReader;
import com.navi.rental.client.CommandReaderFactory;
import com.navi.rental.exceptions.CommandExecutionException;
import com.navi.rental.execution.CommandFactory;

import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) {
        CommandFactory commandFactory = CommandFactory.initialize();
        try {
            CommandReader commandReader = CommandReaderFactory.getReader(args, commandFactory);
            commandReader.handleInput();
        } catch (FileNotFoundException ex) {
            System.out.println("The supplied input file was not found!");
        } catch (CommandExecutionException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Something went wrong. Please try again!" + ex.getMessage());
        }
        //Todo : add logger
    }
}
