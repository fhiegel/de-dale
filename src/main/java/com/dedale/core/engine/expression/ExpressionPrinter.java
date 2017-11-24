package com.dedale.core.engine.expression;

import com.dedale.core.calculator.AddOperation;
import com.dedale.core.calculator.MinusOperation;
import com.dedale.core.calculator.MultiplyOperation;
import com.dedale.core.calculator.PowerOperation;

public class ExpressionPrinter extends ExpressionVisitor<StringBuilder> {

    private final StringBuilder sb = new StringBuilder();

    public ExpressionPrinter() {
        this
                .when(AddOperation.class, e -> sb.append("+"))
                .when(MinusOperation.class, e -> sb.append("-"))
                .when(MultiplyOperation.class, e -> sb.append("*"))
                .when(PowerOperation.class, e -> sb.append("^"))
                
                .when(BoldTextExpression.class, e -> sb.append("*"))
                .when(ValuedExpression.class, e -> sb.append(e.value()))
                .when(ConcatCommand.class, e -> sb.append(ConcatCommand.CONCAT_SEPARATOR))
                .otherwise(e -> sb.append('{').append(e.getClass()).append('}'));
    }

    @Override
    public StringBuilder visit(Expression receiver) {
        if (receiver == null) {
            return sb.append("null");
        }
        return super.visit(receiver);
    }

    public String print() {
        return sb.toString();
    }

}