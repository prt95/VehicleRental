package com.navi.rental.client;

import com.navi.rental.execution.CommandFactory;

import java.io.BufferedReader;

/*
   added FileClient for testing purpose
 */
public class FileCommandReader extends CommandReader {
    public FileCommandReader(BufferedReader inputReader, CommandFactory commandFactory) {
        super(inputReader, commandFactory);
    }
}
