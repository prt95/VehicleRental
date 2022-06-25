package execution;

import com.navi.rental.commands.AddBranchCommand;
import com.navi.rental.commands.AddVehicleCommand;
import com.navi.rental.commands.BookCommand;
import com.navi.rental.commands.DisplayVehicleCommand;
import com.navi.rental.exceptions.CommandNotFoundException;
import com.navi.rental.execution.CommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandFactoryTest {
    private static CommandFactory commandFactory;

    @BeforeAll
    public static void init() {
        commandFactory = CommandFactory.initialize();
    }

    @Test
    public void testGetCommand() {
        assertTrue(commandFactory.getCommand("add_branch") instanceof AddBranchCommand);
        assertTrue(commandFactory.getCommand("add_vehicle") instanceof AddVehicleCommand);
        assertTrue(commandFactory.getCommand("book") instanceof BookCommand);
        assertTrue(commandFactory.getCommand("display_vehicles") instanceof DisplayVehicleCommand);
    }

    @Test
    public void testGetCommandInvalidCommand() {
        assertThrows(CommandNotFoundException.class, () -> commandFactory.getCommand("invalid_command"));
    }

}
