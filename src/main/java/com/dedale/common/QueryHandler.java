package com.dedale.common;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface QueryHandler<R, Q extends Query<R>> {

    R handle(Q query);
    
    Class<Q> listenTo();
    
}
