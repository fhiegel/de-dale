package com.dedale.calculator;

import com.dedale.core.CommandDefinitions;
import com.dedale.core.ExecutionContext;
import com.dedale.core.InterpreterEngine;
import com.dedale.core.InterpreterModule;
import com.dedale.core.expression.AddOperation;
import com.dedale.core.expression.Expression;
import com.dedale.core.expression.ExpressionPrinter;
import com.dedale.core.expression.IntegerExpression;
import com.dedale.core.expression.MinusOperation;
import com.dedale.core.expression.MultiplyOperation;
import com.dedale.core.expression.PowerOperation;
import com.dedale.dice.DiceOperation;

public class StringCalculator implements InterpreterModule {

    private final CommandDefinitions commandDefinitions;

    public StringCalculator(CommandDefinitions statements) {
        this.commandDefinitions = statements;
    }

    public String calculate(String sentence) {
        InterpreterEngine engine = new InterpreterEngine(this);
        ExecutionContext context = ExecutionContext.from(engine);
        
        Expression expression = engine.interpret(context, sentence);

        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return commandDefinitions;
    }

    public static CommandDefinitions calculatorStatements() {
        return CommandDefinitions
                .defineCommands()
                .andParse("\\d+", e -> new IntegerExpression(Integer.valueOf(e)))
                .withCommand("[+]", AddOperation.EMPTY)
                .withCommand("([-])(?!-)", MinusOperation.EMPTY)
                .withCommand("[*]", MultiplyOperation.EMPTY)
                .withCommand("[\\^]", PowerOperation.EMPTY)
                .withCommand("([dD])", DiceOperation::new);
    }

}
