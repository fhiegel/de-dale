package com.dedale.core.engine;

public class ExecutionContext {

    private InterpreterEngine engine;

    private ExecutionContext(InterpreterEngine engine) {
        this.engine = engine;
    }

    public InterpreterEngine engine() {
        return engine;
    }

    static ExecutionContext from(InterpreterEngine engine) {
        return new ExecutionContext(engine);
    }

}
