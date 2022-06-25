package commands;

import com.navi.rental.VehicleType;
import com.navi.rental.commands.BookCommand;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookCommandTest {
    private static BookCommand command = new BookCommand();

    @Test
    public void testParseCommandValidInput() {
        assertTrue(command.parse(new String[]{"B1", "VAN", "1", "5"}));
    }

    @Test
    public void testParseCommandInvalidInput() {
        assertThrows(InvalidCommandParameterException.class, () -> command.parse(new String[]{"B1", "VAN", "invalid_time", "invalid_time"}));
    }

    @Test
    public void testParseCommandParametersPopulatedCorrectly() {
        assertTrue(command.parse(new String[]{"B1", "VAN", "1", "5"}));
        assertEquals(command.getbranchId(), "B1");
        assertEquals(command.getVehicleType(), VehicleType.VAN);
        assertEquals(command.getStartTime(), 1);
        assertEquals(command.getEndTime(), 5);
    }
}
