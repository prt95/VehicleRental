package com.navi.rental.service;

import com.navi.rental.Branch;
import com.navi.rental.VehicleType;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    boolean onboardBranch(String branchName, List<VehicleType> vehicleTypeList);

    Optional<Branch> getBranchById(String branchId);
}
