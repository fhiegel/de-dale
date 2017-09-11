package com.dedale.calculator;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class StringCalculatorFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(StringCalculator.arithmeticOperations(), StringCalculator.class);
        return true;
    }
}
