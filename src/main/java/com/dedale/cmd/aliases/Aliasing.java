package com.dedale.cmd.aliases;

import com.dedale.core.CommandDefinitions;
import com.dedale.core.ExecutionContext;
import com.dedale.core.InterpreterModule;
import com.dedale.core.expression.TextExpression;

public class Aliasing implements InterpreterModule {

    final Aliases userAlias = new Aliases();
    private final InterpreterModule delegate;

    public Aliasing() {
        this(InterpreterModule.EMPTY);
    }

    public Aliasing(InterpreterModule module) {
        this.delegate = module;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        CommandDefinitions commands = delegate.configure(context);
        return commands
                .withModule("alias", _context -> aliasingCommands())
                .softlyAddCommands(aliases(context));
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
