package com.dedale.core.engine;

import com.dedale.core.engine.expression.Expression;

class CommandKeyword {

    private final String keyword;

    private CommandKeyword(String keyword) {
        this.keyword = keyword;
    }

    public static CommandKeyword of(String keyword) {
        return new CommandKeyword(keyword);
    }

    public CommandParser removeThen(CommandParser parser) {
        return new RemoveKeyword(parser);
    }

    private class RemoveKeyword implements CommandParser {

        private CommandParser delegate;

        public RemoveKeyword(CommandParser delegate) {
            this.delegate = delegate;
        }

        @Override
        public Expression parse(String input) {
            return delegate.parse(input.replaceFirst(keyword, "").trim());
        }
    }

    @Override
    public String toString() {
        return keyword;
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
        CommandKeyword other = (CommandKeyword) obj;
        if (keyword == null) {
            if (other.keyword != null)
                return false;
        } else if (!keyword.equals(other.keyword))
            return false;
        return true;
    }

}
