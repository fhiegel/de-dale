package com.dedale.core.engine;

import com.dedale.core.engine.expression.Expression;

public interface CommandParser {
    
    Expression parse(String input);
    
}
