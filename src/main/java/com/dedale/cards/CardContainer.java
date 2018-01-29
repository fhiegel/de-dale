package com.dedale.cards;

import java.util.Collection;
import java.util.Collections;

public class CardContainer {

    private final Collection<Card> cards;

    public CardContainer() {
        this(Collections.emptyList());
    }

    public CardContainer(Collection<Card> cards) {
        this.cards = Collections.unmodifiableCollection(cards);
    }

    public Collection<Card> getCards() {
        return cards;
    }

}
