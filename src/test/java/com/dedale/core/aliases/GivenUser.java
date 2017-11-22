package com.dedale.core.aliases;

import com.dedale.core.aliases.Alias;
import com.dedale.core.aliases.Aliases;
import com.dedale.core.aliases.Aliasing;

public class GivenUser {

    private Aliases userAliases;

    public GivenUser(Aliasing aliasesEngine, String userName) {
        this.userAliases = aliasesEngine.userAlias;
    }

    public void hasAlias(String name, String command) {
        userAliases.addAlias(new Alias(name, command));
    }

}
