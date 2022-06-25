package com.navi.rental;

import java.util.List;

public class Branch {
    String branchId;
    List<VehicleType> vehicleTypeList;

    public Branch(String branchName, List<VehicleType> vehicleTypeList) {
        this.branchId = branchName;
        this.vehicleTypeList = vehicleTypeList;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
