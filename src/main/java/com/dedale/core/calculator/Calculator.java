package com.dedale.core.calculator;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.TextExpression;
import com.dedale.dice.DiceOperation;

public class Calculator implements CommandModule {

    private final CommandModule module;

    public Calculator() {
        this(CommandModule.EMPTY);
    }

    Calculator(CommandDefinitions commands) {
        this(c -> commands);
    }

    private Calculator(CommandModule module) {
        this.module = module;
    }

    @Override
    public CommandDefinitions configure(ExecutionContext context) {
        return module.configure(context)
                .flatAdd(calculatorStatements());
    }

    protected CommandDefinitions calculatorStatements() {
        return CommandDefinitions
                .defineCommands()
                .andParse("\\d+", e -> new IntegerExpression(Integer.valueOf(e)))
                .withCommand("[+]", AddOperation.EMPTY)
                .withCommand("([-])(?!-)", MinusOperation.EMPTY)
                .withCommand("[*]", MultiplyOperation.EMPTY)
                .withCommand("[\\^]", PowerOperation.EMPTY)
                .withCommand("([dD])(?=\\d)", DiceOperation::new)
                .andParse("\\S+", TextExpression::new);
    }

}
