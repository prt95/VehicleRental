package service;

import com.navi.rental.Branch;
import com.navi.rental.VehicleType;
import com.navi.rental.service.impl.BranchServiceImpl;
import com.navi.rental.storage.BranchDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;

public class BranchServiceImplTest {
    private BranchDao branchDao = Mockito.mock(BranchDao.class);

    @InjectMocks
    private BranchServiceImpl service = new BranchServiceImpl(branchDao);

    @BeforeAll
    public static void init() {
    }

    @Test
    public void testOnboardBranch() {
        Mockito.when(branchDao.save(any(Branch.class))).thenReturn(true);
        assertTrue(service.onboardBranch("B1", Arrays.asList(VehicleType.CAR)));
    }


}
