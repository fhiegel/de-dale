package com.dedale.core.calculator;

import com.dedale.core.aliases.Aliasing;
import com.dedale.core.aliases.InMemoryUserAliases;
import com.dedale.core.aliases.UserAliases;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.text.Texting;
import io.micronaut.context.annotation.Factory;

import javax.inject.Named;
import javax.inject.Singleton;

@Factory
public class CalculatingFeatures {

    public static final String ENGINE = "calculator";

    @Named(ENGINE)
    @Singleton
    public InterpreterEngine calculator() {
        UserAliases userAliases = new InMemoryUserAliases();
        CommandModule calculator = new Texting(new Aliasing(new Calculator(), userAliases));
        return new InterpreterEngine(calculator);
    }

}
