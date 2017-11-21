package com.dedale.core;

import java.util.regex.Matcher;
import java.util.stream.Stream;

class CommandCompilationUnit {

    private static final CommandCompilationUnit DOES_NOT_COMPILE = new CommandCompilationUnit(false, CommandArguments.NONE, "");

    private final boolean compiles;
    private final CommandArguments arguments;
    private final String remainingArguments;

    private CommandCompilationUnit(boolean compiles, CommandArguments arguments, String remainingArguments) {
        this.compiles = compiles;
        this.arguments = arguments;
        this.remainingArguments = remainingArguments;
    }

    private CommandCompilationUnit(CommandArguments arguments, String remainingArguments) {
        this(true, arguments, remainingArguments);
    }

    public static CommandCompilationUnit compiles(CommandPattern pattern, String input) {
        Matcher matcher = pattern.compiles(input);
        return matcher.find() ? new CommandCompilationUnit(pattern.argumentsFor(input), matcher.replaceFirst("").trim()) : DOES_NOT_COMPILE;
    }

    public boolean compiles() {
        return compiles;
    }

    public CommandLineItem command(CommandDefinition definition) {
        return definition.toCommandWithArgument(arguments);
    }

    public Stream<String> remainingArguments() {
        if (remainingArguments.isEmpty()) {
            return Stream.empty();
        }
        return Stream.of(remainingArguments);
    }

}
