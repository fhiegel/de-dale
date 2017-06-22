package com.dedale.cards;

import javax.inject.Inject;

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

}
