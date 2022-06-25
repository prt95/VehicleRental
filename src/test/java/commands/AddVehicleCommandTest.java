package commands;

import com.navi.rental.VehicleType;
import com.navi.rental.commands.AddVehicleCommand;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddVehicleCommandTest {
    private static AddVehicleCommand command = new AddVehicleCommand();

    @Test
    public void testParseCommandValidInput() {
        assertTrue(command.parse(new String[]{"B1", "VAN", "V1", "300"}));
    }

    @Test
    public void testParseCommandInvalidInput() {
        assertThrows(InvalidCommandParameterException.class, () -> command.parse(new String[]{"B1", "VAN", "V1", "invalid_price"}));
    }

    @Test
    public void testParseCommandParametersPopulatedCorrectly() {
        assertTrue(command.parse(new String[]{"B1", "VAN", "V1", "300"}));
        assertEquals(command.getbranchId(), "B1");
        assertEquals(command.getVehicleId(), "V1");
        assertEquals(command.getVehicleType(), VehicleType.VAN);
        assertEquals(command.getPricePerHour(), 300);
    }
}
