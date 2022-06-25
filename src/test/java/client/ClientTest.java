package client;

import com.navi.rental.client.CLICommandReader;
import com.navi.rental.client.CommandReader;
import com.navi.rental.execution.CommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {
    private static CommandFactory commandFactory;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() throws FileNotFoundException {
        commandFactory = CommandFactory.initialize();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testHandleInputExitCommandDoesnotThrowException() {
        CommandReader commandReader = new CLICommandReader(
                new BufferedReader(new StringReader("exit")),
                commandFactory
        );
        assertDoesNotThrow(() -> commandReader.handleInput());

    }

    @Test
    public void testHandleInputInvalidCommandThrowsException() throws IOException {
        CommandReader commandReader = new CLICommandReader(
                new BufferedReader(new StringReader("invalid_command")),
                commandFactory
        );
        commandReader.handleInput();
        assertTrue(outputStreamCaptor.toString()
                .trim().contains("Command execution failed:"));

    }

}
