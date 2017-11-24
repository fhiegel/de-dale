package com.dedale.core.aliases;

import com.dedale.core.User;
import com.dedale.core.aliases.Alias;
import com.dedale.core.aliases.Aliases;
import com.dedale.core.aliases.Aliasing;

public class GivenUser {

    private Aliases aliases;

    public GivenUser(UserAliases userAliases, User user) {
        this.aliases = userAliases.getAliases(user);
    }

    public void hasAlias(String name, String command) {
        aliases.addAlias(new Alias(name, command));
    }

}
