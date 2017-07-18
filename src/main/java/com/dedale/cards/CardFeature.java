package com.dedale.cards;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.dedale.cards.query.AddCardCommandHandler;
import com.dedale.cards.query.FindCardsByIdsQueryHandler;
import com.dedale.cards.query.GetAllCardsQueryHandler;
import com.dedale.common.Dispatcher;
import com.dedale.common.QueryHandler;

public class CardFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(CardResource.class);
        context.register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(Dispatcher.class).to(Dispatcher.class);

                bind(GetAllCardsQueryHandler.class).to(GetAllCardsQueryHandler.class).to(QueryHandler.class);
                bind(AddCardCommandHandler.class).to(AddCardCommandHandler.class).to(QueryHandler.class);
                bind(FindCardsByIdsQueryHandler.class).to(FindCardsByIdsQueryHandler.class).to(QueryHandler.class);
                bind(CardRepositoryImpl.class).to(CardRepository.class);
            }
        });

        return true;
    }

}
