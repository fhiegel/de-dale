package com.dedale.core.aliases;

public class Alias {

    final String name;
    final String commandLine;

    public Alias(String name, String commandLine) {
        this.name = name;
        this.commandLine = commandLine;
    }

    public String name() {
        return name;
    }

    public String commandLine() {
        return commandLine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commandLine == null) ? 0 : commandLine.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Alias other = (Alias) obj;
        if (commandLine == null) {
            if (other.commandLine != null)
                return false;
        } else if (!commandLine.equals(other.commandLine))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alias [name=" + name + ", commandLine=" + commandLine + "]";
    }

}
