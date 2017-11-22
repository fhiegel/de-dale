package com.dedale.core.engine.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class ExpressionVisitor<R> {

    protected final Map<Class<? extends Expression>, Function<Expression, R>> expressionToEvaluation = new LinkedHashMap<>();

    protected Function<Expression, R> defaultEvaluation = e -> {
        throw new IllegalArgumentException("Didn't configure expression : " + e.getClass());
    };

    public <T extends Expression> ExpressionVisitor<R> when(Class<? extends T> type, Function<? super T, R> expressionEvaluation) {
        expressionToEvaluation.put(type, expressionEvaluation.compose(type::cast));
        return this;
    }

    public ExpressionVisitor<R> otherwise(Function<Expression, R> defaultEvaluation) {
        this.defaultEvaluation = defaultEvaluation;
        return this;
    }

    public R visit(Expression receiver) {
        Function<Expression, R> expressionMapper = find(receiver);
        return expressionMapper.apply(receiver);
    }

    private Function<Expression, R> find(Expression receiver) {
        return expressionToEvaluation
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAssignableFrom(receiver.getClass()))
                .findFirst()
                .map(Entry::getValue)
                .orElse(defaultEvaluation);
    }

}
