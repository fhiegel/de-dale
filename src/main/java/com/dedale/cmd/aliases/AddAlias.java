package com.dedale.cmd.aliases;

import java.util.function.Supplier;

import com.dedale.core.ExecutionContext;
import com.dedale.core.expression.Expression;

public class AddAlias implements Expression {

    private String arguments;
    private Supplier<Aliases> userAliases;

    public AddAlias(String arguments, Supplier<Aliases> userAliases) {
        this.arguments = arguments.trim();
        this.userAliases = userAliases;
    }

    // public AddAlias(String arguments, Supplier<AliasesEngine> engine) {
    // this.arguments = arguments;
    // this.engine = engine;
    // }

    @Override
    public Expression then(Expression after) {
        return this;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Aliases aliases = userAliases.get();

        String name = aliasName();
        String commandLine = getCommandLine();
        aliases.addAlias(name, commandLine);
        return new GetAliases(aliases);
    }

    private String aliasName() {
        return arguments.split("=")[0];
    }

    private String getCommandLine() {
        return removeQuotes(arguments.split("=", 2)[1]);
    }

    private String removeQuotes(String argument) {
        return argument.replaceFirst("^\"", "").replaceFirst("\"$", "");
    }

}
