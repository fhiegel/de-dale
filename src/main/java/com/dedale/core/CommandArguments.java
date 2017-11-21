package com.dedale.core;

class CommandArguments {

    public static final CommandArguments NONE = new CommandArguments("");

    private final String arguments;

    CommandArguments(String arguments) {
        this.arguments = arguments;
    }

    public String arguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return arguments;
    }
}
