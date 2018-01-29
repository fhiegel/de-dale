package com.dedale.common;

import java.util.List;

/**
 * @deprecated Should not name "Respository" a repository
 */
@Deprecated
public interface Repository<I extends Identifiable> {
    
    I getById(long id);
    
    void add(I item);
    
    void delete(I item);
    
    List<I> getAll();
    
}
