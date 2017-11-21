package com.dedale.core;

import java.util.function.Function;
import java.util.function.Supplier;

import com.dedale.core.expression.Expression;

public class CommandDefinitionParserBuilder {

    private final CommandKeyword keyword;
    private final CommandPattern capturingPattern;
    private final Function<CommandDefinition, CommandDefinitions> commandAssigner;
    private CommandDefinition commandDefinition;

    CommandDefinitionParserBuilder(CommandKeyword keyword, CommandPattern capturingPattern,
            Function<CommandDefinition, CommandDefinitions> commandAssigner) {
        this.keyword = keyword;
        this.capturingPattern = capturingPattern;
        this.commandAssigner = commandAssigner;
    }

    public CommandDefinitionParserBuilder constant(Expression constantExpression) {
        CommandDefinition expressionParser = new CommandDefinition(keyword, capturingPattern, empty -> constantExpression);
        return addCommand(expressionParser);
    }

    public CommandDefinitionParserBuilder provider(Supplier<Expression> supplier) {
        CommandDefinition expressionParser = new CommandDefinition(keyword, capturingPattern, empty -> supplier.get());
        return addCommand(expressionParser);
    }

    public CommandDefinitionParserBuilder parser(CommandParser parser) {
        CommandDefinition expressionParser = new CommandDefinition(keyword, capturingPattern, parser);
        return addCommand(expressionParser);
    }

    public CommandDefinitionParserBuilder removeKeywordThen(CommandParser parser) {
        return parser(keyword.removeThen(parser));
    }

    private CommandDefinitionParserBuilder addCommand(CommandDefinition commandDefinition) {
        this.commandDefinition = commandDefinition;
        return this;
    }

    public CommandDefinitions build() {
        return commandAssigner.apply(commandDefinition);
    }

}
