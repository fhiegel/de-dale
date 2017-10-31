package com.dedale.core.parse;

import com.dedale.core.expression.Expression;

public class CommandLine {

    final RawArguments raw;
    final Expression experssion;

    public CommandLine(String input, ExpressionParsers parsers) {
        this(new RawArguments(input, parsers));
    }

    public CommandLine(RawArguments raw) {
        this(raw, Expression.NEUTRAL);
    }

    private CommandLine(RawArguments raw, Expression expression) {
        this.raw = raw;
        this.experssion = expression;
    }

    public Expression expression() {
        if (isFullyParsed()) {
            return experssion;
        }
        Expression next = raw.nextExpression();
        CommandLine commandLine = new CommandLine(raw.consume(), experssion.then(next));
        return commandLine.expression();
    }

    private boolean isFullyParsed() {
        return raw.isEmpty();
    }

}
