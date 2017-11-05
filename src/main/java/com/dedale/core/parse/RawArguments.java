package com.dedale.core.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dedale.core.expression.Expression;

class RawArguments {

    private final String[] arguments;
    private final Function<String, Expression> parser;

    private RawArguments(String[] arguments, Function<String, Expression> parser) {
        this.arguments = arguments;
        this.parser = parser;
    }

    public RawArguments(String input, ExpressionParsers parsers) {
        this(splitArguments(input, parsers.pattern()), cli -> parsers.parse(cli));
    }

    static String[] splitArguments(String input, String pattern) {
        ArrayList<String> arguments = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        while (matcher.find()) {
            arguments.add(matcher.group());
        }
        return arguments.toArray(new String[arguments.size()]);
    }

    public boolean isEmpty() {
        return arguments.length <= 0;
    }

    public String next() {
        return arguments[0];
    }

    public Expression nextExpression() {
        return parser.apply(next());
    }

    public RawArguments consume() {
        String[] remainingArguments = Arrays.copyOfRange(arguments, 1, arguments.length);
        return new RawArguments(remainingArguments, parser);
    }

    @Override
    public String toString() {
        return "RawArguments{arguments=" + arguments + "}";
    }
}