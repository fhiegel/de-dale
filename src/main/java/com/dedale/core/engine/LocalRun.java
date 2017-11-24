package com.dedale.core.engine;

import com.dedale.core.engine.expression.Expression;

final class LocalRun implements Expression {
    private final String commandLine;
    private final InterpreterEngine localEngine;

    LocalRun(CommandModule module, String commandLine) {
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

    static CommandParser runModule(CommandModule module) {
        return commandLine -> new LocalRun(module, commandLine);
    }
}