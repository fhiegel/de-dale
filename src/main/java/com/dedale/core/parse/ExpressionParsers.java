package com.dedale.core.parse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import com.dedale.core.InterpreterEngine;
import com.dedale.core.expression.Expression;

public class ExpressionParsers {

    private final Map<String, ExpressionParser> parsersByPatterns = new LinkedHashMap<>();

    public ExpressionParsers when(String pattern, ExpressionParsers subParsers) {
        return when(pattern, new InterpreterEngine(subParsers));
    }

    private ExpressionParsers when(String pattern, InterpreterEngine subEngine) {
        return when(pattern + ".+$", subcmd -> subEngine.interpret(subcmd.replaceFirst(pattern, "")));
    }

    public ExpressionParsers when(String pattern, Function<String, Expression> parser) {
        parsersByPatterns.put(pattern, new ExpressionParser(pattern, parser));
        return this;
    }

    public String pattern() {
        return String.join("|", parsersByPatterns.keySet());
    }

    private ExpressionParser findParser(String statement) {
        return parsersByPatterns.values().stream().filter(parser -> parser.matches(statement)).findFirst().orElseThrow(
                () -> new IllegalArgumentException("Cannot find parser for expression : \"" + statement + " \"."));
    }

    Expression parse(String statement) {
        return findParser(statement).parse(statement);
    }

}
