package com.dedale;

import org.glassfish.jersey.server.ResourceConfig;

import com.dedale.calculator.StringCalculatorFeature;
import com.dedale.slack.client.SlackClientFeature;

public class DeDaleResourceConfig extends ResourceConfig {
    
    public DeDaleResourceConfig() {
        packages("com.dedale.api");
        register(StringCalculatorFeature.class);
        register(SlackClientFeature.class);
    }
    
}
