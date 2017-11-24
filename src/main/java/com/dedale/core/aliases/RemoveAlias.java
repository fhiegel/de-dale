package com.dedale.core.aliases;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.Expression;

public class RemoveAlias implements Expression {

    private String aliasName;
    private UserAliases userAliases;

    public RemoveAlias(String aliasName, UserAliases userAliases) {
        this.aliasName = aliasName;
        this.userAliases = userAliases;
    }

    @Override
    public Expression then(Expression after) {
        return this;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Aliases aliases = userAliases.getAliases(context.user());
        aliases.remove(aliasName);
        return new GetAliases(aliases);
    }

}
