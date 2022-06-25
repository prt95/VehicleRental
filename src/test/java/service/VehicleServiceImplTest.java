package service;

import com.navi.rental.Vehicle;
import com.navi.rental.VehicleType;
import com.navi.rental.service.impl.VehicleServiceImpl;
import com.navi.rental.storage.VehicleDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;

public class VehicleServiceImplTest {
    private VehicleDao vehicleDao = Mockito.mock(VehicleDao.class);

    @InjectMocks
    private VehicleServiceImpl service = new VehicleServiceImpl(vehicleDao);

    @BeforeAll
    public static void init() {
    }

    @Test
    public void testBooking() {
        Vehicle vehicle = new Vehicle("V1", VehicleType.CAR, 200);
        Mockito.when(vehicleDao.getVehicleByBranchId("B1")).thenReturn(Arrays.asList(vehicle));
        Assertions.assertIterableEquals(service.getVehicleByBranchId("B1"), Arrays.asList(vehicle));
    }

    @Test
    public void testOnboardVehicle() {
        Mockito.when(vehicleDao.save(any(Vehicle.class))).thenReturn(true);
        Vehicle vehicle = new Vehicle("V1", VehicleType.CAR, 200);
        assertTrue(service.addVehicleToBranch("B1", vehicle));
        assertEquals(vehicle.getBranchId(), "B1");
    }
}
