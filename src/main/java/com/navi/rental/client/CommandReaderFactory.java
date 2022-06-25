package com.navi.rental.client;

import com.navi.rental.execution.CommandFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class CommandReaderFactory {

    public static CommandReader getReader(String[] args, CommandFactory commandFactory) throws FileNotFoundException {
        if (args.length == 0) {
            return new CLICommandReader(new BufferedReader(new InputStreamReader(System.in)), commandFactory);
        } else {
            return new FileCommandReader(new BufferedReader(new FileReader(args[0])), commandFactory);
        }
    }
}