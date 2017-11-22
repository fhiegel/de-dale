package com.dedale.core.aliases;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.TextExpression;

public class Aliasing implements CommandModule {

    private final CommandModule module;
    
    final Aliases userAlias = new Aliases();

    public Aliasing() {
        this(CommandModule.EMPTY);
    }

    public Aliasing(CommandModule module) {
        this.module = module;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return module.configure(context)
                .flatAdd(aliasModule(context));
    }

    private CommandDefinitions aliasModule(ExecutionContext context) {
        return CommandDefinitions.defineCommands()
                .withModule("alias", aliasingCommands())
                .flatAdd(aliases(context));
    }

    private CommandDefinitions aliases(ExecutionContext context) {
        CommandDefinitions aliases = CommandDefinitions.defineCommands();
        for (Alias alias : userAlias) {
            aliases.andParse(alias.name).strictly().with().provider(() -> context.engine().interpret(context, alias.commandLine)).build();
        }
        return aliases;
    }

    private CommandDefinitions aliasingCommands() {
        return CommandDefinitions
                .defineCommands()
                .withParameterizedCommand("add", args -> new AddAlias(args, () -> userAlias))
                .withCommand("--help", new TextExpression("Help text"))
                .withDefault(new ListAliases(() -> userAlias));
    }

}
