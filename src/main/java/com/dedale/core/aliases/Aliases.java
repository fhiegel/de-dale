package com.dedale.core.aliases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Aliases implements Iterable<Alias> {

    private final Collection<Alias> aliases = new ArrayList<>();

    @Override
    public Iterator<Alias> iterator() {
        return aliases.iterator();
    }

    public void addAlias(Alias alias) {
        aliases.add(alias);
    }

    public void addAlias(String name, String commandLine) {
        addAlias(new Alias(name, commandLine));
    }

    @Override
    public String toString() {
        return "Aliases [aliases=" + aliases + "]";
    }

    public void remove(String aliasName) {
        aliases.stream()
                .filter(a -> a.name.equals(aliasName))
                .findFirst()
                .ifPresent(aliases::remove);
    }

}
