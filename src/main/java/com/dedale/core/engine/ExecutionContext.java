package com.dedale.core.engine;

import java.util.Objects;

import com.dedale.core.User;

public class ExecutionContext {

    private final InterpreterEngine engine;
    private final User user;
    private final String input;

    private ExecutionContext(InterpreterEngine engine) {
        this(engine, User.NONE, "");
    }

    private ExecutionContext(InterpreterEngine engine, User user, String input) {
        this.engine = Objects.requireNonNull(engine);
        this.user = Objects.requireNonNull(user);
        this.input = Objects.requireNonNull(input);
    }

    static ExecutionContext from(InterpreterEngine engine) {
        return new ExecutionContext(engine);
    }

    public ExecutionContext withUser(String userId) {
        return new ExecutionContext(engine, new User(userId), input);
    }

    public ExecutionContext withUserName(String userName) {
        return new ExecutionContext(engine, user.withName(userName), input);
    }

    public ExecutionContext input(String input) {
        return new ExecutionContext(engine, user, input);
    }

    public InterpreterEngine engine() {
        return engine;
    }

    public User user() {
        return user;
    }

    public String input() {
        return input;
    }

}
