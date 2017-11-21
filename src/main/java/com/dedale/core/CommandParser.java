package com.dedale.core;

import java.util.function.Function;

import com.dedale.core.expression.Expression;

public interface CommandParser {
    Expression parse(String input);

    static CommandParser from(Function<String, Expression> fn) {
        return e -> fn.apply(e);
    }
}
