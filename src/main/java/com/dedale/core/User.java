package com.dedale.core;

public class User {

    public static final User NONE = new User("", "");

    private final String userId;

    private final String name;

    public User(String userId) {
        this(userId, userId);
    }

    private User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "User {userId=" + userId + ", name=" + name + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public User withName(String name) {
        return new User(userId, name);
    }

}
