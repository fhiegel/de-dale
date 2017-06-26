package com.dedale.cards.query;

import javax.inject.Inject;

import com.dedale.cards.Card;
import com.dedale.cards.CardRepository;
import com.dedale.common.QueryHandler;

public class AddCardCommandHandler implements QueryHandler<Card, AddCardCommand> {

    private CardRepository repository;

    @Inject
    public AddCardCommandHandler(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Card handle(AddCardCommand query) { 
        Card card = query.getCard();
        repository.add(card);
        return card;
    }

    @Override
    public Class<AddCardCommand> listenTo() {
        return AddCardCommand.class;
    }

}
