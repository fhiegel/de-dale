package com.dedale.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CommandCompiler {

    private CommandDefinitions commands;

    public CommandCompiler(CommandDefinitions commands) {
        this.commands = commands;
    }

    public CommandLine compile(String input) {
        return new CommandLine(compileItems(input));
    }

    private Collection<CommandLineItem> compileItems(String input) {
        Collection<CommandLineItem> commandLineItems = new ArrayList<>();
        for (CommandDefinition definition : commands) {
            CommandCompilationUnit compilationUnit = definition.compiles(input);
            if (compilationUnit.compiles()) {
                commandLineItems.add(compilationUnit.command(definition));
                commandLineItems.addAll(compilesRemainings(compilationUnit));
                return commandLineItems;
            }
        }
        return Collections.emptyList();
    }

    private List<CommandLineItem> compilesRemainings(CommandCompilationUnit compilationUnit) {
        return compilationUnit.remainingArguments()
                .map(this::compileItems).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
