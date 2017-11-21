package com.dedale.core;

import java.util.Collection;

import com.dedale.core.expression.Expression;

class CommandLine {

    private final Collection<CommandLineItem> commandLineItems;

    CommandLine(Collection<CommandLineItem> commandLineItems) {
        this.commandLineItems = commandLineItems;
    }

    public Expression expression() {
        Expression expression = Expression.NEUTRAL;
        for (CommandLineItem commandLineItem : commandLineItems) {
            Expression command = commandLineItem.createCommand();
            expression = expression.then(command);
        }
        return expression;
    }

}
