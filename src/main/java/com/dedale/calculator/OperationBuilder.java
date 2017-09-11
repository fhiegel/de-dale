package com.dedale.calculator;

import java.util.function.Function;

public class OperationBuilder<NB> {

    private Function<? super StringToIntegerOperation, NB> operationAssigner;
    private String symbol;
    private OperandReader operandReader;
    private IntegerOperation integerOperation;

    public OperationBuilder(Function<? super StringToIntegerOperation, NB> operationAssigner) {
        this.operationAssigner = operationAssigner;
    }

    public OperationBuilder<NB> withSymbols(String... symbols) {
        return withSymbol("[" + String.join("", symbols) + "]");
    }

    public OperationBuilder<NB> withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public OperationBuilder<NB> fromLeftToRight() {
        return withOperandReader(OperandReader.LEFT_TO_RIGHT);
    }

    public OperationBuilder<NB> fromRightToLeft() {
        return withOperandReader(OperandReader.RIGHT_TO_LEFT);
    }

    OperationBuilder<NB> withOperandReader(OperandReader operandReader) {
        this.operandReader = operandReader;
        return this;
    }

    public OperationBuilder<NB> withOperation(IntegerOperation integerOperation) {
        this.integerOperation = integerOperation;
        return this;
    }

    // Finalize building

    public NB endOperation() {
        StringToIntegerOperation operation = build();
        return operationAssigner.apply(operation);
    }

    private StringToIntegerOperation build() {
        return new StringToIntegerOperation(symbol, operandReader, integerOperation);
    }

}
