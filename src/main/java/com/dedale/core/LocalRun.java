package com.dedale.core;

import com.dedale.core.expression.Expression;

final class LocalRun implements Expression {
    private final String commandLine;
    private final InterpreterEngine localEngine;

    LocalRun(InterpreterModule module, String commandLine) {
        this.localEngine = new InterpreterEngine(module);
        this.commandLine = commandLine;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        return localEngine.interpret(context, commandLine);
    }

    @Override
    public Expression then(Expression after) {
        return this;
    }

    static CommandParser runModule(InterpreterModule module) {
        return commandLine -> new LocalRun(module, commandLine);
    }
}