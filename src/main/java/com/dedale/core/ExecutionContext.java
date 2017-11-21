package com.dedale.core;

public class ExecutionContext {

    private InterpreterEngine engine;

    private ExecutionContext(InterpreterEngine engine) {
        this.engine = engine;
    }

    public InterpreterEngine engine() {
        return engine;
    }

    public static ExecutionContext from(InterpreterEngine engine) {
        return new ExecutionContext(engine);
    }

}
