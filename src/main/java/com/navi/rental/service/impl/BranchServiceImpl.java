package com.navi.rental.service.impl;

import com.navi.rental.Branch;
import com.navi.rental.VehicleType;
import com.navi.rental.service.BranchService;
import com.navi.rental.storage.BranchDao;

import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {
    private BranchDao branchDao;

    public BranchServiceImpl(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public boolean onboardBranch(String branchName, List<VehicleType> vehicleTypeList) {
        Branch branch = new Branch(branchName, vehicleTypeList);
        return branchDao.save(branch);
    }

    @Override
    public Optional<Branch> getBranchById(String branchId) {
        return branchDao.get(branchId);
    }

}
