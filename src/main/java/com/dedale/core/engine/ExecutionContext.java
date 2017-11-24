package com.dedale.core.engine;

import com.dedale.core.User;

public class ExecutionContext {

    private final InterpreterEngine engine;
    private final User user;

    private ExecutionContext(InterpreterEngine engine) {
        this(engine, User.NONE);
    }

    private ExecutionContext(InterpreterEngine engine, User user) {
        this.engine = engine;
        this.user = user;
    }

    static ExecutionContext from(InterpreterEngine engine) {
        return new ExecutionContext(engine);
    }

    public ExecutionContext withUser(String userId) {
        return new ExecutionContext(engine, new User(userId));
    }

    public InterpreterEngine engine() {
        return engine;
    }

    public User user() {
        return user;
    }

}
