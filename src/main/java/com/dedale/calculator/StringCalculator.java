package com.dedale.calculator;

import com.dedale.core.InterpreterEngine;
import com.dedale.core.expression.AddOperation;
import com.dedale.core.expression.Expression;
import com.dedale.core.expression.IntegerExpression;
import com.dedale.core.expression.MinusOperation;
import com.dedale.core.expression.MultiplyOperation;
import com.dedale.core.expression.PowerOperation;
import com.dedale.core.parse.ExpressionParsers;
import com.dedale.dice.DiceOperation;

public class StringCalculator {

    private InterpreterEngine delegateInterpreter;

    public StringCalculator() {
        this(calculatorStatements());
    }
    public StringCalculator(ExpressionParsers statements) {
        delegateInterpreter = new InterpreterEngine(statements);
    }

    public String calculate(String sentence) {
        Expression result = delegateInterpreter.interpret(sentence);
        return result.print();
    }

    public static ExpressionParsers calculatorStatements() {
        ExpressionParsers statements = new ExpressionParsers();
        return statements
                .when("\\d+", e -> new IntegerExpression(Integer.valueOf(e)))
                .when("[+]", e -> AddOperation.EMPTY)
                .when("([-])(?!-)", e -> MinusOperation.EMPTY)
                .when("[*]", e -> MultiplyOperation.EMPTY)
                .when("[\\^]", e -> PowerOperation.EMPTY)
                .when("([dD])", e -> new DiceOperation());
    }

}
