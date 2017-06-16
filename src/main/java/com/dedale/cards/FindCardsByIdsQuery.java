package com.dedale.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class FindCardsByIdsQuery implements Query {
    
    private static final String ID_SEPARATOR = ",";
    
    private List<Long> cardIds = Collections.emptyList();
    
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
    
}
