package service;

import com.navi.rental.Booking;
import com.navi.rental.Branch;
import com.navi.rental.Vehicle;
import com.navi.rental.VehicleType;
import com.navi.rental.service.impl.BookingServiceImpl;
import com.navi.rental.storage.BookingDao;
import com.navi.rental.storage.BranchDao;
import com.navi.rental.storage.VehicleDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingServiceImplTest {
    private BranchDao branchDao = Mockito.mock(BranchDao.class);
    private BookingDao bookingDao = Mockito.mock(BookingDao.class);
    private VehicleDao vehicleDao = Mockito.mock(VehicleDao.class);

    @InjectMocks
    private BookingServiceImpl service = new BookingServiceImpl(branchDao, bookingDao, vehicleDao);

    @BeforeAll
    public static void init() {
    }

    @Test
    public void testBooking() {
        Branch branch = new Branch("B1", Arrays.asList(VehicleType.CAR));
        Vehicle vehicle = new Vehicle("V1", VehicleType.CAR, 200);
        vehicle.setBranchId("B1");
        Mockito.when(branchDao.get("B1")).thenReturn(java.util.Optional.of(branch));
        Mockito.when(vehicleDao.getVehicleByBranchId("B1")).thenReturn(Arrays.asList(vehicle));


        assertTrue(service.book("B1", VehicleType.CAR, 1, 5) == 800);

    }

    @Test
    public void testNoBookingAvailable() {
        Branch branch = new Branch("B1", Arrays.asList(VehicleType.CAR));
        Vehicle vehicle = new Vehicle("V1", VehicleType.CAR, 200);
        vehicle.setBranchId("B1");
        Mockito.when(branchDao.get("B1")).thenReturn(java.util.Optional.of(branch));
        Booking booking = new Booking(vehicle, 2000, 2, 3, 1);
        Mockito.when(bookingDao.getBookingsByVehicleId("V1")).thenReturn(Arrays.asList(booking));
        Mockito.when(vehicleDao.getVehicleByBranchId("B1")).thenReturn(Arrays.asList(vehicle));
        assertTrue(service.book("B1", VehicleType.CAR, 1, 5) == -1);

    }

    @Test
    public void testListAvailableVehicles() {
        Branch branch = new Branch("B1", Arrays.asList(VehicleType.CAR));
        Vehicle vehicle1 = new Vehicle("V1", VehicleType.CAR, 200);
        Vehicle vehicle2 = new Vehicle("V2", VehicleType.CAR, 200);
        vehicle1.setBranchId("B1");
        Mockito.when(branchDao.get("B1")).thenReturn(java.util.Optional.of(branch));
        Booking booking = new Booking(vehicle1, 2000, 2, 3, 1);
        Mockito.when(bookingDao.getBookingsByVehicleId("V1")).thenReturn(Arrays.asList(booking));
        Mockito.when(vehicleDao.getVehicleByBranchId("B1")).thenReturn(Arrays.asList(vehicle1, vehicle2));
        Assertions.assertIterableEquals(service.listVehicles("B1", 1, 5),Arrays.asList(vehicle2));
    }


}
