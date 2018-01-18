package com.dedale.cards.query;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import com.dedale.cards.Card;
import com.dedale.cards.CardRepository;
import com.dedale.common.QueryHandler;

@Service
public class AddCardCommandHandler implements QueryHandler<Card, AddCardCommand> {

    @Inject
    private CardRepository repository;

    public AddCardCommandHandler() {
    }

    public AddCardCommandHandler(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<AddCardCommand> listenTo() {
        return AddCardCommand.class;
    }

    @Override
    public Card handle(AddCardCommand query) {
        Card card = query.getCard();
        repository.add(card);
        return card;
    }

}
