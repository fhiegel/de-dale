package com.dedale;

import com.dedale.cards.CardFeature;
import com.dedale.core.calculator.CalculatingFeatures;
import com.dedale.slack.SlackFeatures;
import com.dedale.utils.JsonConfiguration;
import org.glassfish.jersey.server.ResourceConfig;


public class DeDaleResourceConfig extends ResourceConfig {

    public DeDaleResourceConfig() {
        packages("com.dedale.api");
        register(CalculatingFeatures.class);
        register(SlackFeatures.class);
        register(JsonConfiguration.class);

        register(CardFeature.class);
    }

}
