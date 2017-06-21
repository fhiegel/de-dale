package com.dedale.utils.jersey;

import org.junit.runners.model.Statement;

abstract class StatementDecorator extends Statement {
    
    private Statement statement;
    
    public StatementDecorator(Statement statement) {
        this.statement = statement;
    }
    
    @Override
    public final void evaluate() throws Throwable {
        prepareStatement();
        statement.evaluate();
        releaseStatement();
    }
    
    protected void prepareStatement() throws Throwable {
        
    }
    
    protected void releaseStatement() throws Throwable {
        
    }
    
}
