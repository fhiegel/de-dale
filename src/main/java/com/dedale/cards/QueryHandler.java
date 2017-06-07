package com.dedale.cards;

public interface QueryHandler<R, Q extends Query> {

    R handle(Q query);
    
}
