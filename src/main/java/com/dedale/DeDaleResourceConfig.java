package com.dedale;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;
import com.dedale.cards.CardFeature;
import com.dedale.slack.client.SlackClientFeature;
import com.dedale.utils.JsonConfiguration;

public class DeDaleResourceConfig extends ResourceConfig {
    
    public DeDaleResourceConfig() {
        packages("com.dedale.api");
        register(JsonConfiguration.class);
        
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(StringCalculator.class).to(StringCalculator.class);
                bind(StringCalculatorInputValidator.class).to(StringCalculatorInputValidator.class);
            }
        });
        
        register(SlackClientFeature.class);
        register(CardFeature.class);
    }
    
}
