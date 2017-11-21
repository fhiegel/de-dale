package com.dedale.core;

import java.util.function.Function;

public class CommandDefinitionPatternBuilder {

    private static final String CAPTURE_FOLLOWING_ARGUMENTS = ".*";
    private static final String STARTS_WITH = "^";
    private static final String ENDS = "$";

    private final CommandKeyword keyword;
    private final String capturingPattern;
    private final Function<CommandDefinition, CommandDefinitions> commandAssigner;

    private CommandDefinitionPatternBuilder(CommandKeyword keyword, String capturingPattern, Function<CommandDefinition, CommandDefinitions> commandAssigner) {
        this.keyword = keyword;
        this.capturingPattern = capturingPattern;
        this.commandAssigner = commandAssigner;
    }

    // Caputring Pattern Builder

    public CommandDefinitionPatternBuilder(CommandKeyword keyword, Function<CommandDefinition, CommandDefinitions> commandAssigner) {
        this(keyword, STARTS_WITH + keyword, commandAssigner);
    }

    public CommandDefinitionPatternBuilder capturingFollowingArguments() {
        return new CommandDefinitionPatternBuilder(keyword, capturingPattern() + CAPTURE_FOLLOWING_ARGUMENTS, commandAssigner);
    }

    public CommandDefinitionPatternBuilder strictly() {
        return new CommandDefinitionPatternBuilder(keyword, capturingPattern() + ENDS, commandAssigner);
    }

    // Command Builder

    public CommandDefinitionParserBuilder with() {
        return new CommandDefinitionParserBuilder(keyword, new CommandPattern(capturingPattern), commandAssigner);
    }

    //
    // Utils
    //

    private String capturingPattern() {
        return capturingPattern;
    }
}