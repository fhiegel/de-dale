package com.dedale;

import org.glassfish.jersey.server.ResourceConfig;

import com.dedale.core.calculator.CalculatingFeatures;
import com.dedale.slack.SlackFeatures;

public class DeDaleResourceConfig extends ResourceConfig {

    public DeDaleResourceConfig() {
        packages("com.dedale.api");
        register(CalculatingFeatures.class);
        register(SlackFeatures.class);
    }

}
