package com.navi.rental.client;

import com.navi.rental.execution.CommandFactory;

import java.io.BufferedReader;

public class CLICommandReader extends CommandReader {

    public CLICommandReader(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}
