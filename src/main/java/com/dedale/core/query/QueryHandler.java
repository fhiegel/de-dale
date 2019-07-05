package com.dedale.core.query;

public interface QueryHandler<R, Q extends Query<R>> {

    R handle(Q query);
    
    Class<Q> listenTo();
    
}
