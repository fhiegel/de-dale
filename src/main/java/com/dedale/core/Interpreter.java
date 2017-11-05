package com.dedale.core;

import com.dedale.core.expression.Expression;

public interface Interpreter {

    Expression interpret(String input);

}
