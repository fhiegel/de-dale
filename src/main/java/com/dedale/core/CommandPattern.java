package com.dedale.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CommandPattern {

    private final String pattern;

    CommandPattern(String pattern) {
        this.pattern = pattern;
    }

    public CommandArguments argumentsFor(String input) {
        Matcher matcher = compiles(input);
        return matcher.find() ? new CommandArguments(matcher.group()) : CommandArguments.NONE;
    }

    public Matcher compiles(String input) {
        return Pattern.compile(pattern).matcher(input);
    }

    @Override
    public String toString() {
        return pattern;
    }

}
