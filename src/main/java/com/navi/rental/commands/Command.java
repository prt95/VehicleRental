package com.navi.rental.commands;

import com.navi.rental.exceptions.BranchDoesNotExistException;
import com.navi.rental.exceptions.CommandExecutionException;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.BranchService;
import com.navi.rental.service.impl.BranchServiceImpl;
import com.navi.rental.storage.BranchDao;
import org.apache.commons.lang3.StringUtils;

public abstract class Command {
    private String branchId;
    private String helpText = "";
    private BranchService branchService = new BranchServiceImpl(BranchDao.getInstance());

    public final void process(String[] params) throws CommandExecutionException {
        this.parse(params);
        if (!this.validate()) {
            throw new CommandExecutionException("validation failed");
        }
        this.execute();
    }

    public boolean parse(String[] params) throws InvalidCommandParameterException {
        this.branchId = params[0];
        return true;
    }

    public abstract boolean validate() throws CommandExecutionException;

    public boolean validateBranchExists(String branchId) {
        if (StringUtils.isEmpty(branchId)) {
            System.out.println("Branch name shouldn't be empty");
            return false;
        }
        if (!branchService.getBranchById(branchId).isPresent()) {
            throw new BranchDoesNotExistException("Branch doesn't exist");
        }
        return true;
    }

    public abstract boolean execute();

    public String getbranchId() {
        return branchId;
    }

    public void setbranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public BranchService getBranchService() {
        return branchService;
    }
}
