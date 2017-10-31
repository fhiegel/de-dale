package com.dedale.core.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

public class ExpressionVisitor {

    protected final Map<Class<? extends Expression>, Function<Expression, ? extends Expression>> expressionToEvaluation = new LinkedHashMap<>();

    protected Function<Expression, ? extends Expression> defaultEvaluation = Function.identity();

    public <T extends Expression> ExpressionVisitor when(Class<? extends T> type,
            Function<? super T, ? extends Expression> expressionEvaluation) {
        expressionToEvaluation.put(type, expressionEvaluation.compose(type::cast));
        return this;
    }

    public ExpressionVisitor otherwise(Function<Expression, ? extends Expression> defaultEvaluation) {
        this.defaultEvaluation = defaultEvaluation;
        return this;
    }

    public Expression visit(Expression receiver) {
        Function<Expression, ? extends Expression> expressionMapper = find(receiver);
        return expressionMapper.apply(receiver);
    }

    private Function<Expression, ? extends Expression> find(Expression receiver) {
        Optional<Function<Expression, ? extends Expression>> expressionMapper = expressionToEvaluation
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAssignableFrom(receiver.getClass()))
                .findFirst()
                .map(Entry::getValue);
        return expressionMapper.orElse(defaultEvaluation);
    }

}
