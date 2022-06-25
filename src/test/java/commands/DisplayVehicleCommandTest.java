package commands;

import com.navi.rental.commands.DisplayVehicleCommand;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayVehicleCommandTest {
    private static DisplayVehicleCommand command = new DisplayVehicleCommand();

    @Test
    public void testParseCommandValidInput() {
        assertTrue(command.parse(new String[]{"B1", "1", "6"}));
    }

    @Test
    public void testParseCommandInvalidInput() {
        assertThrows(InvalidCommandParameterException.class, () -> command.parse(new String[]{"B1", "invalid_time", "invalid_time"}));
    }

    @Test
    public void testParseCommandParametersPopulatedCorrectly() {
        assertTrue(command.parse(new String[]{"B1", "1", "6"}));
        assertEquals(command.getbranchId(), "B1");
        assertEquals(command.getStartTime(), 1);
        assertEquals(command.getEndTime(), 6);
    }
}
