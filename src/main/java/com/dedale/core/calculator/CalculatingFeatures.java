package com.dedale.core.calculator;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.dedale.core.aliases.Aliasing;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.InterpreterEngine;

public class CalculatingFeatures implements Feature {

    public static final String ENGINE = "calculator";

    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                CommandModule calculator = new Aliasing(new Calculator());
                bind(new InterpreterEngine(calculator)).named(ENGINE).to(InterpreterEngine.class);
            }
        });
        return true;
    }

}
