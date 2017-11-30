package com.dedale.core.aliases;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.RawText;
import com.dedale.utils.resources.Resources;

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
        return CommandDefinitions.defineCommands()
                .flatAdd(aliasingCommands())
                .flatAdd(aliases(context));
    }

    private CommandDefinitions aliases(ExecutionContext context) {
        CommandDefinitions aliases = CommandDefinitions.defineCommands();
        for (Alias alias : userAliases.getAliases(context.user())) {
            aliases
                    .andParse(alias.name)
                    .strictly()
                    .with()
                    .provider(() -> context.engine().interpret(context.withInput(alias.commandLine)))
                    .build();
        }
        return aliases;
    }

    private CommandDefinitions aliasingCommands() {
        return CommandDefinitions
                .defineCommands()
                .withParameterizedCommand("alias", args -> new AddAlias(args, userAliases))
                .andParse("alias").strictly().with().constant(new ListAliases(userAliases)).build()
                .withParameterizedCommand("alias add", args -> new AddAlias(args, userAliases))
                .withParameterizedCommand("unalias", args -> new RemoveAlias(args, userAliases))
                .withParameterizedCommand("alias remove", args -> new RemoveAlias(args, userAliases))
                .withCommand("alias --help", () -> new RawText(Resources.getRelativeTo(Aliasing.class, "HELP.md").asString()));
    }

}
