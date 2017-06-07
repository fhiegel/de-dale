package com.dedale.cards;

import java.util.Collection;
import java.util.Collections;

import lombok.Getter;

@Getter
public class CardContainer {
    
    private Collection<Card> cards = Collections.emptyList();
    
    public CardContainer() {
    }
    
    public CardContainer(Collection<Card> cards) {
        this.cards = Collections.unmodifiableCollection(cards);
    }

}
