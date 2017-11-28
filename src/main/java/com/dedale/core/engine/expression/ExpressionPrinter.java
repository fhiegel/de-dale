package com.dedale.core.engine.expression;

import com.dedale.core.calculator.AddOperation;
import com.dedale.core.calculator.MinusOperation;
import com.dedale.core.calculator.MultiplyOperation;
import com.dedale.core.calculator.PowerOperation;
import com.dedale.dice.DiceResult;

public class ExpressionPrinter extends ExpressionVisitor<StringBuilder> {

    public ExpressionPrinter() {
        this(new StringBuilder());
        this
                .when(AddOperation.class, e -> result.append("+"))
                .when(MinusOperation.class, e -> result.append("-"))
                .when(MultiplyOperation.class, e -> result.append("*"))
                .when(PowerOperation.class, e -> result.append("^"))

                .when(BoldTextExpression.class, e -> result.append("*"))
                .when(ValuedExpression.class, e -> result.append(e.value()))
                .when(DiceResult.class, e -> result.append(e.value()))
                .when(ConcatCommand.class, e -> result.append(ConcatCommand.CONCAT_SEPARATOR))
                .when(Neutral.class, e -> result.append(e))
                .otherwise(e -> result.append('{').append(e.getClass()).append('}'));
    }

    private ExpressionPrinter(StringBuilder stringBuilder) {
        super(stringBuilder);
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