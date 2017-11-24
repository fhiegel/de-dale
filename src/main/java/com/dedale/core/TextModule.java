package com.dedale.core;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.BoldTextExpression;
import com.dedale.core.engine.expression.TextExpression;

public class TextModule implements CommandModule {

    private final CommandModule module;

    TextModule() {
        this(CommandModule.EMPTY);
    }

    TextModule(CommandDefinitions commands) {
        this(c -> commands);
    }

    public TextModule(CommandModule module) {
        this.module = module;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return module.configure(context)
                .flatAdd(textModule());
    }

    protected CommandDefinitions textModule() {
        return CommandDefinitions
                .defineCommands()
                .andParse("--bold", e -> BoldTextExpression.EMPTY)
                .andParse("\\S+", TextExpression::new);
    }

}
