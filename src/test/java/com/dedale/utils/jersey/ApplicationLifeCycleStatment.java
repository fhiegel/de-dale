package com.dedale.utils.jersey;

import org.junit.runners.model.Statement;

class ApplicationLifeCycleStatment extends StatementDecorator {

    private ApplicationCycleManager cycleManager;

    public ApplicationLifeCycleStatment(Statement statement, ApplicationCycleManager rule) {
        super(statement);
        this.cycleManager = rule;
    }

    @Override
    protected void prepareStatement() throws Throwable {
        cycleManager.prepareTestCycle();
    }

    @Override
    protected void releaseStatement() throws Throwable {
        cycleManager.releaseTestCycle();
    }
}