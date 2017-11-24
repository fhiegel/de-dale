package com.dedale.core.aliases;

import java.util.HashMap;
import java.util.Map;

import com.dedale.core.User;

public class InMemoryUserAliases implements UserAliases {

    private final Map<User, Aliases> inMemoryRepository = new HashMap<>();

    @Override
    public Aliases getAliases(User user) {
        Aliases aliases = inMemoryRepository.get(user);
        if (aliases == null) {
            aliases = initialize(user);
        }
        return aliases;
    }

    private Aliases initialize(User user) {
        Aliases aliases = new Aliases();
        inMemoryRepository.put(user, aliases);
        return aliases;
    }

}
