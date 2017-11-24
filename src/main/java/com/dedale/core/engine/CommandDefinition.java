package com.dedale.core.engine;

class CommandDefinition {

    private final CommandKeyword keyword;
    private final CommandPattern pattern;
    private final CommandParser parser;

    public CommandDefinition(CommandKeyword keyword, CommandPattern pattern, CommandParser parser) {
        this.keyword = keyword;
        this.pattern = pattern;
        this.parser = parser;
    }

    public CommandCompilationUnit compiles(String input) {
        return CommandCompilationUnit.compiles(pattern, input);
    }

    public CommandLineItem toCommandWithArgument(CommandArguments arguments) {
        return new CommandLineItem(parser, arguments);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{argumentsPattern=" + pattern + ", parser=" + parser + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
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
        CommandDefinition other = (CommandDefinition) obj;
        if (keyword == null) {
            if (other.keyword != null)
                return false;
        } else if (!keyword.equals(other.keyword))
            return false;
        return true;
    }

}
