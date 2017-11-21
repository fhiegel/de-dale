package com.dedale.core;

public interface InterpreterModule {

    InterpreterModule EMPTY = context -> CommandDefinitions.defineCommands();

    CommandDefinitions configure(ExecutionContext context);

}
