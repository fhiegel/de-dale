package com.dedale.core.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<I extends Identifiable> implements Repository<I> {
    
    private Map<Long, I> inMemoryItems = new HashMap<>();
    private long nextIdSequence = 1L;
    
    @Override
    public I getById(long id) {
        return inMemoryItems.get(id);
    }
    
    @Override
    public void add(I item) {
        inMemoryItems.put(hash(item), item);
    }
    
    private long hash(I item) {
        return hasValidId(item) ? item.getId() : defineIdentifiableId(item).getId();
    }
    
    private boolean hasValidId(I item) {
        return item.getId() > 0;
    }
    
    private Identifiable defineIdentifiableId(I item) {
        item.setId(nextId());
        return item;
    }
    
    private long nextId() {
        return nextIdSequence++;
    }
    
    @Override
    public void delete(I item) {
        inMemoryItems.remove(hash(item));
    }
    
    @Override
    public List<I> getAll() {
        return List.copyOf(inMemoryItems.values());
    }
    
}
