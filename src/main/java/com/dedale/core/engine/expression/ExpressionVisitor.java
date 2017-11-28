package com.dedale.core.engine.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public abstract class ExpressionVisitor<R> {

    protected final R result;
    protected final Map<Class<? extends Expression>, Function<Expression, R>> callbacks = new LinkedHashMap<>();

    protected Function<Expression, R> defaultEvaluation = e -> {
        throw new IllegalArgumentException("Didn't configure expression : " + e.getClass());
    };

    public ExpressionVisitor(R result) {
        this.result = result;
    }

    @SuppressWarnings("unchecked")
    public <T extends Expression, V extends ExpressionVisitor<R>> V when(Class<? extends T> type,
            Function<? super T, R> expressionEvaluation) {
        callbacks.put(type, expressionEvaluation.compose(type::cast));
        return (V) this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Expression, V extends ExpressionVisitor<R>> V otherwise(Function<Expression, R> defaultEvaluation) {
        this.defaultEvaluation = defaultEvaluation;
        return (V) this;
    }

    public <V extends ExpressionVisitor<R>> V visit(Expression receiver) {
        Function<Expression, R> expressionMapper = find(receiver);
        R result = expressionMapper.apply(receiver);
        return visitor(result);
    }

    protected abstract <V extends ExpressionVisitor<R>> V visitor(R result);

    private Function<Expression, R> find(Expression receiver) {
        return callbacks
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAssignableFrom(receiver.getClass()))
                .findFirst()
                .map(Entry::getValue)
                .orElse(defaultEvaluation);
    }

}
