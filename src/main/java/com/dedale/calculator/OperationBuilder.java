package com.dedale.calculator;

import java.util.function.Consumer;

class OperationBuilder<NB> {
    
    private Consumer<? super StringToIntegerOperation> operationConsumer;
    private NB nextBuilder;
    
    private String symbol;
    private OperandReader operandReader;
    private IntegerOperation integerOperation;
    
    OperationBuilder(Consumer<? super StringToIntegerOperation> operationConsumer, NB nextBuilder) {
        this.operationConsumer = operationConsumer;
        this.nextBuilder = nextBuilder;
    }
    
    public OperationBuilder<NB> withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
    
    public OperationBuilder<NB> withOperandReader(OperandReader operandReader) {
        this.operandReader = operandReader;
        return this;
    }
    
    public OperationBuilder<NB> withOperation(IntegerOperation integerOperation) {
        this.integerOperation = integerOperation;
        return this;
    }
    
    // Finalize building
    
    private void consumeBean(StringToIntegerOperation operation) {
        if (operationConsumer == null) {
            return;
        }
        operationConsumer.accept(operation);
        operationConsumer = null; // Toute consommation est unique.
    }
    
    public NB endOperation() {
        StringToIntegerOperation operation = build();
        consumeBean(operation);
        return nextBuilder;
    }
    
    public StringToIntegerOperation build() {
        return new StringToIntegerOperation(symbol, operandReader, integerOperation);
    }
    
}
