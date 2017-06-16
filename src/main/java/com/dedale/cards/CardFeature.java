package com.dedale.cards;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class CardFeature implements Feature {
    
    @Override
    public boolean configure(FeatureContext context) {
        context.register(CardResource.class);
        context.register(new AbstractBinder() {
            
            @Override
            protected void configure() {
                bind(FindCardsByIdsQueryHandler.class).to(FindCardsByIdsQueryHandler.class);
                bind(AddCardCommandHandler.class).to(AddCardCommandHandler.class);
            }
        });
        return true;
    }
    
}
