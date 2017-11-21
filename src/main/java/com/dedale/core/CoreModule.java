package com.dedale.core;

import com.dedale.calculator.StringCalculator;
import com.dedale.core.expression.BoldTextExpression;
import com.dedale.core.expression.TextExpression;

public class CoreModule implements InterpreterModule {

    private final CommandDefinitions commands;

    public CoreModule(CommandDefinitions commands) {
        this.commands = commands;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return commands;
    }

    static CommandDefinitions expressionParser() {
        CommandDefinitions aParser = CommandDefinitions.defineCommands();
        return aParser
                .withModule("roll", StringCalculator.calculatorStatements())
                .withModule("recursivesubcmd", aParser)
                .andParse("show", e -> new TextExpression("..."))
                .andParse("--bold", e -> BoldTextExpression.EMPTY)
                .andParse("\\S+", TextExpression::new);
    }

}
