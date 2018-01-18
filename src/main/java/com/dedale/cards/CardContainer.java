package com.dedale.cards;

import java.util.Collection;
import java.util.Collections;


public class CardContainer {
    
    private Collection<Card> cards = Collections.emptyList();

    public CardContainer() {

    }

    public CardContainer(Collection<Card> cards) {
        this.cards = Collections.unmodifiableCollection(cards);
    }

    public Collection<Card> getCards() {
        return cards;
    }

}
