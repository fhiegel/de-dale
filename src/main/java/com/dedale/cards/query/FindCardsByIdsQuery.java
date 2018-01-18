package com.dedale.cards.query;

import com.dedale.cards.CardContainer;
import com.dedale.common.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindCardsByIdsQuery implements Query<CardContainer> {
    
    private static final String ID_SEPARATOR = ",";
    
    private List<Long> cardIds;
    
    private FindCardsByIdsQuery(List<Long> cardIds) {
        this.cardIds = Collections.unmodifiableList(cardIds);
    }
    
    public static FindCardsByIdsQuery parse(String cardIds) {
        return new FindCardsByIdsQuery(Arrays.asList(cardIds.split(ID_SEPARATOR)).stream()
                .map(Long::valueOf)
                .collect(Collectors.toList()));
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cardIds == null) ? 0 : cardIds.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FindCardsByIdsQuery other = (FindCardsByIdsQuery) obj;
        if (cardIds == null) {
            if (other.cardIds != null)
                return false;
        } else if (!cardIds.equals(other.cardIds))
            return false;
        return true;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }
}
