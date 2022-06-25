package commands;

import com.navi.rental.Branch;
import com.navi.rental.VehicleType;
import com.navi.rental.commands.AddBranchCommand;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.BranchService;
import com.navi.rental.storage.BranchDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        assertEquals(command.getBranchName(), "B1");
        Assertions.assertIterableEquals(command.getVehicleTypeList(), Arrays.asList(VehicleType.CAR, VehicleType.VAN));
    }

    @Test
    public void testExecuteCommand() {
        command.setVehicleTypeList(Arrays.asList(VehicleType.CAR, VehicleType.VAN));
        command.setBranchName("B1");
        assertTrue(command.execute());
    }

}
