package com.dedale.core.text;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.BoldTextExpression;
import com.dedale.core.engine.expression.TextExpression;

public class Texting implements CommandModule {

    private final CommandModule module;

    Texting() {
        this(CommandModule.EMPTY);
    }

    Texting(CommandDefinitions commands) {
        this(c -> commands);
    }

    public Texting(CommandModule module) {
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
                .andParse("--bold", e -> BoldTextExpression.BOLD)
                .andParse("\\S+", TextExpression::new);
    }

}
