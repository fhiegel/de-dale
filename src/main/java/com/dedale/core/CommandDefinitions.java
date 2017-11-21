package com.dedale.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

import com.dedale.core.expression.Expression;

public class CommandDefinitions implements Iterable<CommandDefinition> {

    private final Collection<CommandDefinition> commands = new LinkedHashSet<>();

    private CommandDefinitions() {
    }

    public static CommandDefinitions defineCommands() {
        return new CommandDefinitions().withDefault(Expression.NEUTRAL);
    }

    public CommandDefinitions withModule(String keyword, InterpreterModule module) {
        return andParse(keyword).capturingFollowingArguments().with().removeKeywordThen(LocalRun.runModule(module)).build();
    }

    public CommandDefinitions withModule(String keyword, CommandDefinitions module) {
        return withModule(keyword, context -> module);
    }

    public CommandDefinitions addAll(CommandDefinitions commands) {
        this.commands.addAll(commands.commands);
        return this;
    }

    public CommandDefinitions softlyAddCommands(CommandDefinitions commands) {
        this.commands.addAll(commands.commands);
        return this;
    }

    public CommandDefinitions withParameterizedCommand(String keyword, CommandParser parser) {
        return andParse(keyword).capturingFollowingArguments().with().removeKeywordThen(parser).build();
    }

    public CommandDefinitions withCommand(String keyword, Expression constantExpression) {
        return andParse(keyword).with().constant(constantExpression).build();
    }

    public CommandDefinitions withCommand(String keyword, Supplier<Expression> supplier) {
        return andParse(keyword).strictly().with().provider(supplier).build();
    }

    public CommandDefinitions withDefault(Expression defaultCommand) {
        return andParse("").strictly().with().constant(defaultCommand).build();
    }

    public CommandDefinitions withDefault(Supplier<Expression> supplier) {
        return andParse("").strictly().with().provider(supplier).build();
    }

    public CommandDefinitions andParse(String pattern, CommandParser parser) {
        return andParse(pattern).with().parser(parser).build();
    }

    public CommandDefinitionPatternBuilder andParse(String keyword) {
        return new CommandDefinitionPatternBuilder(CommandKeyword.of(keyword), this::overrideParser);
    }

    private CommandDefinitions overrideParser(CommandDefinition commandParser) {
        commands.remove(commandParser);
        commands.add(commandParser);
        return this;
    }

    @Override
    public Iterator<CommandDefinition> iterator() {
        return commands.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Commands :").append(System.lineSeparator());
        commands.forEach(c -> sb.append("\t").append(c).append(System.lineSeparator()));
        return sb.toString();
    }

}
