package com.dedale.cards;

import com.dedale.cards.query.AddCardHandler;
import com.dedale.cards.query.FindCardsByIdsHandler;
import com.dedale.cards.query.GetAllCardsHandler;
import com.dedale.core.query.Dispatcher;
import com.dedale.core.query.QueryHandler;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;
import java.util.List;

@Factory
public class CardFeature  {

    @Singleton
    Dispatcher dispatcher(List<QueryHandler<?, ?>> handlers) {
        return new Dispatcher(handlers);
    }

    @Singleton
    Cards getCards() {
        CardRepositoryImpl cards = new CardRepositoryImpl();
        return cards;
    }

    @Singleton
    GetAllCardsHandler getAllCardsHandler(Cards cards) {
        return new GetAllCardsHandler(cards);
    }

    @Singleton
    AddCardHandler addCardHandler(Cards cards) {
        return new AddCardHandler(cards);
    }

    @Singleton
    FindCardsByIdsHandler findCardsByIdsHandler(Cards cards) {
        return new FindCardsByIdsHandler(cards);
    }

}
