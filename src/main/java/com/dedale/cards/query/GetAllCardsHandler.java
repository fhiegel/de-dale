package com.dedale.cards.query;

import com.dedale.cards.CardContainer;
import com.dedale.cards.Cards;
import com.dedale.core.query.QueryHandler;
import io.micronaut.context.annotation.Prototype;

import javax.inject.Inject;
import javax.inject.Singleton;

public class GetAllCardsHandler implements QueryHandler<CardContainer, GetAllCards> {

    private final Cards cards;

    public GetAllCardsHandler(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Class<GetAllCards> listenTo() {
        return GetAllCards.class;
    }

    public CardContainer handle(GetAllCards getAllCardsCommand) {
        return new CardContainer(cards.getAll());
    }


}
