package com.dedale.core.engine;

public interface CommandModule {

    CommandModule EMPTY = context -> CommandDefinitions.defineCommands();

    CommandDefinitions configure(ExecutionContext context);

}
