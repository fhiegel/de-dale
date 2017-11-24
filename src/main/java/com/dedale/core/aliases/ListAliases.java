package com.dedale.core.aliases;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.Expression;

public class ListAliases implements Expression {

    private final UserAliases userAlias;

    public ListAliases(UserAliases userAlias) {
        this.userAlias = userAlias;
    }

    @Override
    public Expression then(Expression after) {
        return after;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Aliases aliases = userAlias.getAliases(context.user());
        return new GetAliases(aliases);
    }
}
