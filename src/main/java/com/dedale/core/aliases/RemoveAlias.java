package com.dedale.core.aliases;

import java.util.function.Supplier;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.Expression;

public class RemoveAlias implements Expression {

    private String aliasName;
    private Supplier<Aliases> userAliases;

    public RemoveAlias(String aliasName, Supplier<Aliases> userAliases) {
        this.aliasName = aliasName;
        this.userAliases = userAliases;
    }

    @Override
    public Expression then(Expression after) {
        return this;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Aliases aliases = userAliases.get();
        aliases.remove(aliasName);
        return new GetAliases(aliases);
    }

}
