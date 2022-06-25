package client;

import com.navi.rental.client.CLICommandReader;
import com.navi.rental.client.CommandReader;
import com.navi.rental.client.CommandReaderFactory;
import com.navi.rental.client.FileCommandReader;
import com.navi.rental.execution.CommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandReaderFactoryTest {
    private static CommandFactory commandFactory;

    @BeforeAll
    public static void commandFactory() {
        commandFactory = CommandFactory.initialize();
    }

    @Test
    public void testNoArgsGeneratesCliClient() throws FileNotFoundException {
        CommandReader commandReader = CommandReaderFactory.getReader(new String[0], commandFactory);
        assertTrue(commandReader instanceof CLICommandReader);
    }

    @Test
    public void testArgsGeneratesFileClient() throws FileNotFoundException {
        CommandReader commandReader = CommandReaderFactory.getReader(new String[]{"input.txt"}, commandFactory);
        assertTrue(commandReader instanceof FileCommandReader);
    }

    @Test
    public void testInvalidFilePathThrowsException() throws FileNotFoundException {
        assertThrows(FileNotFoundException.class, () -> CommandReaderFactory.getReader(new String[]{"invalid_input.txt"}, commandFactory));
    }
}
