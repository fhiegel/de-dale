package com.dedale.core.aliases;

import java.util.function.Supplier;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.Expression;

public class ListAliases implements Expression {

    public static final ListAliases INSTANCE = new ListAliases(() -> null);
    
    private final Supplier<Aliases> userAlias;

    public ListAliases(Supplier<Aliases> userAlias) {
        this.userAlias = userAlias;
    }

    @Override
    public Expression then(Expression after) {
        return after;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        return new GetAliases(userAlias.get());
    }
}
