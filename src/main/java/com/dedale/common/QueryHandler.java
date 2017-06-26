package com.dedale.common;

public interface QueryHandler<R, Q extends Query<R>> {

    R handle(Q query);
    
    Class<Q> listenTo();
    
}
