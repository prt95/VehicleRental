package com.navi.rental.storage;

import com.navi.rental.Branch;
import com.navi.rental.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BranchDao implements Dao {
    private static BranchDao branchDao = new BranchDao();
    List<Branch> branches = new ArrayList<>();

    public static BranchDao getInstance() {
        return branchDao;
    }

    @Override
    public Optional<Branch> get(String id) {
        return (Optional<Branch>) branches.stream().filter(b -> b.getBranchId().equals(id)).findFirst();
    }

    @Override
    public List<Branch> getAll() {
        return branches;
    }


    @Override
    public <T> boolean save(T t) {
        branches.add((Branch) t);
        return true;
    }

    @Override
    public <T> void delete(T t) {
//        branches.stream().filter()
    }

    @Override
    public <T> void update(T t, String[] params) {
        Optional<Branch> optionalBranch = get(((Branch) t).getBranchId());
        Branch branch = optionalBranch.isPresent() ? optionalBranch.get() : null;
        if (branch == null) return;
    }

    public boolean addVehicleToBranch(String branchId, Vehicle vehicle) {

        Optional<Branch> optionalBranch = get(branchId);
        if (!optionalBranch.isPresent()) {
            //log
            return false;
        }
        return true;

    }

}
