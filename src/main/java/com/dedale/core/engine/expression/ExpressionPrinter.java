package com.dedale.core.engine.expression;

import com.dedale.core.calculator.AddOperation;
import com.dedale.core.calculator.MinusOperation;
import com.dedale.core.calculator.MultiplyOperation;
import com.dedale.core.calculator.PowerOperation;
import com.dedale.dice.DiceResult;

public class ExpressionPrinter extends ExpressionVisitor<StringBuilder> {

    public static final String CONCAT_SEPARATOR = " ";
    
    public ExpressionPrinter() {
        this(new StringBuilder());
    }

    private ExpressionPrinter(StringBuilder stringBuilder) {
        super(stringBuilder);
        define();
    }

    protected void define() {
        this
                .when(AddOperation.class, e -> result.append("+"))
                .when(MinusOperation.class, e -> result.append("-"))
                .when(MultiplyOperation.class, e -> result.append("*"))
                .when(PowerOperation.class, e -> result.append("^"))

                .when(BoldTextExpression.class, e -> result.append("*"))
                .when(ValuedExpression.class, e -> result.append(e.value()))
                .when(DiceResult.class, e -> result.append(e.value()))
                .when(Then.class, e -> result.append(CONCAT_SEPARATOR))
                .when(Neutral.class, e -> result.append(e))
                .otherwise(e -> result.append('{').append(e.getClass()).append('}'));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ExpressionPrinter visit(Expression receiver) {
        if (receiver == null) {
            return new ExpressionPrinter(result.append("null"));
        }
        return super.visit(receiver);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ExpressionPrinter visitor(StringBuilder result) {
        return new ExpressionPrinter(result);
    }

    public String print() {
        return result.toString();
    }

}