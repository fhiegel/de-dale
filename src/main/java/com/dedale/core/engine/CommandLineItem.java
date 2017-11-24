package com.dedale.core.engine;

import com.dedale.core.engine.expression.Expression;

class CommandLineItem {

    private CommandArguments arguments;
    private CommandParser parser;

    public CommandLineItem(CommandParser parser, CommandArguments arguments) {
        this.arguments = arguments;
        this.parser = parser;
    }

    public Expression createCommand() {
        return parser.parse(arguments.arguments());
    }

    @Override
    public String toString() {
        return "CommandLineItem [arguments=" + arguments + ", parser=" + parser + "]";
    }
    
}
