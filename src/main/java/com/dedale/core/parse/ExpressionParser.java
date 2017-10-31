package com.dedale.core.parse;

import java.util.function.Function;

import com.dedale.core.expression.Expression;

class ExpressionParser {

    private final String pattern;
    private final Function<String, ? extends Expression> parser;

    public ExpressionParser(String pattern, Function<String, Expression> parser) {
        this.pattern = pattern;
        this.parser = parser;
    }

    public boolean matches(String expression) {
        return expression.matches(pattern);
    }

    public Expression parse(String expression) {
        return parser.apply(expression);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{pattern=" + pattern + ", parser=" + parser + "}";
    }

}
