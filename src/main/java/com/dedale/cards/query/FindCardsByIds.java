package com.dedale.cards.query;

import com.dedale.cards.CardContainer;
import com.dedale.core.query.Query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindCardsByIds implements Query<CardContainer> {
    
    private static final String ID_SEPARATOR = ",";
    
    private List<Long> cardIds;
    
    private FindCardsByIds(List<Long> cardIds) {
        this.cardIds = Collections.unmodifiableList(cardIds);
    }
    
    public static FindCardsByIds parse(String cardIds) {
        return new FindCardsByIds(Arrays.stream(cardIds.split(ID_SEPARATOR))
                                        .map(Long::valueOf)
                                        .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindCardsByIds that = (FindCardsByIds) o;
        return Objects.equals(cardIds, that.cardIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardIds);
    }

    public List<Long> getCardIds() {
        return cardIds;
    }
}
