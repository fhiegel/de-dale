package com.dedale.cmd.aliases;

public class GivenUser {

    private Aliases userAliases;

    public GivenUser(Aliasing aliasesEngine, String userName) {
        this.userAliases = aliasesEngine.userAlias;
    }

    public void hasAlias(String name, String command) {
        userAliases.addAlias(new Alias(name, command));
    }

}
