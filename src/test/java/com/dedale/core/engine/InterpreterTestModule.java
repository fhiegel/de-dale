package com.dedale.core.engine;

import com.dedale.core.calculator.Calculator;
import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.BoldTextExpression;
import com.dedale.core.engine.expression.TextExpression;

public class InterpreterTestModule implements CommandModule {

    private final CommandModule module;

    public InterpreterTestModule() {
        this(CommandModule.EMPTY);
    }

    InterpreterTestModule(CommandDefinitions commands) {
        this(c -> commands);
    }

    private InterpreterTestModule(CommandModule delegate) {
        this.module = delegate;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return module.configure(context).flatAdd(coreModule());
    }

    private CommandDefinitions coreModule() {
        CommandDefinitions aParser = CommandDefinitions.defineCommands();
        return aParser
                .withModule("roll", new Calculator())
                .withModule("recursivesubcmd", aParser)
                .andParse("show", e -> new TextExpression("..."))
                .andParse("--bold", e -> BoldTextExpression.BOLD)
                .andParse("\\S+", TextExpression::new);
    }

}
