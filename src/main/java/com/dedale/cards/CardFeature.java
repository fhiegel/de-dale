package com.dedale.cards;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class CardFeature implements Feature {
    
    @Override
    public boolean configure(FeatureContext context) {
        context.register(CardResource.class);
        return true;
    }
    
}
