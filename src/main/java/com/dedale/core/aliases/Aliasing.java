package com.dedale.core.aliases;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.TextExpression;

public class Aliasing implements CommandModule {

    private final CommandModule module;
    private final UserAliases userAliases;

    Aliasing(CommandDefinitions commands, UserAliases userAlias) {
        this(context -> commands, userAlias);
    }

    public Aliasing(CommandModule module, UserAliases userAlias) {
        this.module = module;
        this.userAliases = userAlias;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return module.configure(context).flatAdd(aliasModule(context));
    }

    private CommandDefinitions aliasModule(ExecutionContext context) {
        return CommandDefinitions.defineCommands().withModule("alias", aliasingCommands()).flatAdd(aliases(context));
    }

    private CommandDefinitions aliases(ExecutionContext context) {
        CommandDefinitions aliases = CommandDefinitions.defineCommands();
        for (Alias alias : userAliases.getAliases(context.user())) {
            aliases.andParse(alias.name).strictly().with().provider(() -> context.engine().interpret(context, alias.commandLine)).build();
        }
        return aliases;
    }

    private CommandDefinitions aliasingCommands() {
        return CommandDefinitions
                .defineCommands()
                .withParameterizedCommand("add", args -> new AddAlias(args, userAliases))
                .withParameterizedCommand("remove", args -> new RemoveAlias(args, userAliases))
                .withCommand("--help", new TextExpression("Help text"))
                .withDefault(new ListAliases(userAliases));
    }

}
