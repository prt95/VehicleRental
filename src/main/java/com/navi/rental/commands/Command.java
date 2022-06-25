package com.navi.rental.commands;

import com.navi.rental.exceptions.BranchDoesNotExistException;
import com.navi.rental.exceptions.CommandExecutionException;
import com.navi.rental.exceptions.InvalidCommandParameterException;
import com.navi.rental.service.BranchService;
import com.navi.rental.service.impl.BranchServiceImpl;
import com.navi.rental.storage.BranchDao;

public abstract class Command {
    private String branchName;
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
        this.branchName = params[0];
        return true;
    }

    public abstract boolean validate() throws CommandExecutionException;

    public boolean validateBranchExists(String branchId) {
        if (!branchService.getBranchById(branchId).isPresent()) {
            throw new BranchDoesNotExistException("Branch doesn't exist");
        }
        return true;
    }

    public abstract boolean execute();

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
