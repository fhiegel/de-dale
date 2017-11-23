package com.dedale.core.aliases;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class Aliases implements Iterable<Alias> {

    private final Collection<Alias> aliases = new TreeSet<>((a1, a2) -> a1.name.compareToIgnoreCase(a2.name));

    @Override
    public Iterator<Alias> iterator() {
        return aliases.iterator();
    }

    public void addAlias(Alias alias) {
        aliases.add(alias);
    }

    public void addAlias(String name, String commandLine) {
        remove(name);
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
