package com.dedale.core;

import com.dedale.calculator.StringCalculator;
import com.dedale.core.expression.BoldTextExpression;
import com.dedale.core.expression.Expression;
import com.dedale.core.expression.TextExpression;
import com.dedale.core.parse.CommandLine;
import com.dedale.core.parse.ExpressionParsers;

public class InterpreterEngine implements Interpreter {

    private final ExpressionParsers statementParsers;

    public InterpreterEngine(ExpressionParsers statementParsers) {
        this.statementParsers = statementParsers;
    }

    @Override
    public Expression interpret(String input) {
        Expression expression = parse(input);
        return expression.evaluate();
    }

    private Expression parse(String input) {
        return new CommandLine(input, statementParsers).expression();
    }

    static ExpressionParsers expressionParser() {
        ExpressionParsers statements = new ExpressionParsers();
        return statements
                .when("roll", StringCalculator.calculatorStatements())
                .when("recursivesubcmd", statements)
                .when("show", e -> new TextExpression("..."))
                .when("--bold", e -> BoldTextExpression.EMPTY)
                .when("\\S+", TextExpression::new);
    }

}
