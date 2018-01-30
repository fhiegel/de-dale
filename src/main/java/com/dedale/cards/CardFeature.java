package com.dedale.cards;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.dedale.cards.query.AddCardHandler;
import com.dedale.cards.query.FindCardsByIdsHandler;
import com.dedale.cards.query.GetAllCardsHandler;
import com.dedale.core.query.Dispatcher;
import com.dedale.core.query.QueryHandler;

public class CardFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(CardResource.class);
        context.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(Dispatcher.class).to(Dispatcher.class);

                CardRepositoryImpl cards = new CardRepositoryImpl();
                bind(cards).to(Cards.class);


                bind(GetAllCardsHandler.class).to(GetAllCardsHandler.class).to(QueryHandler.class);
                bind(AddCardHandler.class).to(AddCardHandler.class).to(QueryHandler.class);
                bind(FindCardsByIdsHandler.class).to(FindCardsByIdsHandler.class).to(QueryHandler.class);
            }
        });

        return true;
    }

}
