package com.dedale.core.engine;

import com.dedale.core.engine.expression.Expression;

public class InterpreterEngine {

    private final CommandModule module;

    public InterpreterEngine(CommandModule module) {
        this.module = module;
    }

    public Expression interpret(ExecutionContext context) {
        CommandCompiler compiler = getCompiler(context);
        CommandLine commandLine = compiler.compile(context.input());
        Expression expression = commandLine.expression();
        return expression.execute(context);
    }

    private CommandCompiler getCompiler(ExecutionContext context) {
        CommandDefinitions commands = getCommands(context);
        return new CommandCompiler(commands);
    }

    private CommandDefinitions getCommands(ExecutionContext context) {
        return module.configure(context);
    }

    public ExecutionContext defaultContext() {
        return ExecutionContext.from(this);
    }

}
