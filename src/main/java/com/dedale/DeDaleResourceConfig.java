package com.dedale;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.dedale.calculator.StringCalculator;
import com.dedale.slack.SlackFeatures;

public class DeDaleResourceConfig extends ResourceConfig {
    
    public DeDaleResourceConfig() {
        packages("com.dedale.api");
        
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                StringCalculator stringCalculator = new StringCalculator(StringCalculator.calculatorStatements());
                bind(stringCalculator).to(StringCalculator.class);
            }
        });
        
        register(SlackFeatures.class);
    }
    
}
