package com.dedale.cards;

import javax.inject.Singleton;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.dedale.cards.query.AddCardCommandHandler;
import com.dedale.cards.query.FindCardsByIdsQueryHandler;
import com.dedale.cards.query.GetAllCardsQueryHandler;

public class CardFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(CardResource.class);
        context.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(FindCardsByIdsQueryHandler.class).to(FindCardsByIdsQueryHandler.class);
                bind(AddCardCommandHandler.class).to(AddCardCommandHandler.class);
                bind(GetAllCardsQueryHandler.class).to(GetAllCardsQueryHandler.class);
                bind(CardRepositoryImpl.class).to(CardRepository.class).in(Singleton.class);
            }
        });

        return true;
    }

}
