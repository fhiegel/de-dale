package com.dedale.core;

import com.dedale.core.expression.Expression;

public class InterpreterEngine {
    
    private InterpreterModule module;

    public InterpreterEngine(InterpreterModule module) {
        this.module = module;
    }

    public Expression interpret(ExecutionContext context, String input) {
        CommandDefinitions commands = getCommands(context);
        CommandCompiler compiler = new CommandCompiler(commands);
        CommandLine commandLine = compiler.compile(input);
        Expression expression = commandLine.expression();
        return expression.execute(context);
    }

    private CommandDefinitions getCommands(ExecutionContext context) {
        return module.configure(context);
    }

}
