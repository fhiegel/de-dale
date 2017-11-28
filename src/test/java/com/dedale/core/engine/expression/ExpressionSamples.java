package com.dedale.core.engine.expression;

import com.dedale.core.calculator.AddOperation;
import com.dedale.core.calculator.IntegerExpression;
import com.dedale.core.calculator.MinusOperation;
import com.dedale.core.calculator.MultiplyOperation;
import com.dedale.core.calculator.PowerOperation;

public class ExpressionSamples {

    public static final Expression one = new IntegerExpression(1);
    public static final Expression two = new IntegerExpression(2);
    public static final Expression three = new IntegerExpression(3);
    public static final Expression four = new IntegerExpression(4);
    public static final Expression seven = new IntegerExpression(7);
    public static final Expression add = AddOperation.EMPTY;
    public static final Expression minus = MinusOperation.EMPTY;
    public static final Expression multiply = MultiplyOperation.EMPTY;
    public static final Expression power = PowerOperation.EMPTY;

    public static final BoldTextExpression bold = BoldTextExpression.EMPTY;
    public static final TextExpression anExpression = text("<any expression>");

    public static final TextExpression text(String text) {
        return new TextExpression(text);
    }
}