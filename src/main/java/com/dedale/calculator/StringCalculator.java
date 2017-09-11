package com.dedale.calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.glassfish.jersey.spi.Contract;

@Contract
public class StringCalculator {

    private final Collection<StringToIntegerOperation> operations;

    private StringCalculator(Collection<StringToIntegerOperation> operations) {
        this.operations = Collections.unmodifiableCollection(operations);
    }

    Collection<StringToIntegerOperation> getOperations() {
        return operations;
    }

    static StringCalculator empty() {
        return new StringCalculator(Collections.emptyList());
    }

    public static StringCalculator arithmeticOperations() {
        return empty().addOperation(ArithmeticOperations.ADD).addOperation(ArithmeticOperations.MINUS)
                .addOperation(ArithmeticOperations.MULTIPLY).addOperation(ArithmeticOperations.POWER);
    }

    public Integer calculate(String sentence) {
        for (StringToIntegerOperation operation : operations) {
            if (operation.mayApplyOperation(sentence)) {
                return operation.apply(sentence, this::calculate);
            }
        }
        return Integer.parseInt(sentence.trim());
    }

    public StringCalculator addOperation(StringToIntegerOperation operation) {
        Collection<StringToIntegerOperation> operations = new ArrayList<>(this.operations);
        Collections.addAll(operations, operation);
        return new StringCalculator(operations);
    }

    public OperationBuilder<StringCalculator> addOperation() {
        return new OperationBuilder<>(this::addOperation);
    }

}
