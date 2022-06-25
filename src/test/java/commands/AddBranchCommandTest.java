package commands;

import com.navi.rental.VehicleType;
import com.navi.rental.commands.AddBranchCommand;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddBranchCommandTest {

    private static AddBranchCommand command = new AddBranchCommand();

    @Test
    public void testParseCommandValidInput() {
        assertTrue(command.parse(new String[]{"B1", "CAR,VAN"}));
    }

    @Test
    public void testParseCommandInvalidInput() {
        assertThrows(InvalidCommandParameterException.class, () -> command.parse(new String[]{"B1", "INVALID_VEHICLE"}));
    }

    @Test
    public void testParseCommandParametersPopulatedCorrectly() {
        assertTrue(command.parse(new String[]{"B1", "CAR,VAN"}));
        assertEquals(command.getbranchId(), "B1");
        Assertions.assertIterableEquals(command.getVehicleTypeList(), Arrays.asList(VehicleType.CAR, VehicleType.VAN));
    }

    @Test
    public void testExecuteCommand() {
        command.setVehicleTypeList(Arrays.asList(VehicleType.CAR, VehicleType.VAN));
        command.setbranchId("B1");
        assertTrue(command.execute());
    }

}
