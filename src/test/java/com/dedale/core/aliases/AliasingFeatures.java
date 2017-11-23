package com.dedale.core.aliases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.TextExpression;

public class AliasingFeatures {

    private Aliasing module = new Aliasing();

    @Test
    public void aliases_are_empty() throws Exception {
        Expression result = engine().interpret("alias");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(GetAliases.class);
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_one() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        Expression cmd = engine().interpret("alias");
        Aliases aliases = ((GetAliases) cmd).value();

        assertThat(aliases).hasSize(1);
        assertThat(aliases).containsExactly(alias());
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_some() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");
        givenUser("anUser").hasAlias("name2", "cmd2");
        givenUser("anUser").hasAlias("name3", "cmd3");

        Expression cmd = engine().interpret("alias");
        Aliases aliases = ((GetAliases) cmd).value();

        assertThat(aliases).hasSize(3);
        assertThat(aliases).containsExactly(alias(), alias("name2", "cmd2"), alias("name3", "cmd3"));
    }

    @Test
    public void an_alias_is_added() throws Exception {
        assumeAliasesAreEmpty();

        engine().interpret("alias add name=cmd");

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void an_alias_is_added_without_quotes() throws Exception {
        assumeAliasesAreEmpty();

        engine().interpret("alias add name=\"cmd\"");

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void print_help() throws Exception {
        Expression cmd = engine().interpret("alias --help");

        TextExpression help = (TextExpression) cmd;
        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void an_alias_is_run() throws Exception {
        assumeAliasesAreEmpty();

        Expression cmd = engine().interpret("alias add doHelp=\"alias --help\"");

        cmd = engine().interpret("doHelp");
        TextExpression help = (TextExpression) cmd;

        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void command_is_run_when_alias_with_same_name_definer() throws Exception {
        module = new Aliasing(CommandDefinitions
                .defineCommands()
                .withCommand("doSomethingElse", new TextExpression("Text when Do Something Else"))
                .withCommand("do", new TextExpression("Should run Do command.")));

        Expression cmd = engine().interpret("alias add do=\"doSomethingElse\"");

        cmd = engine().interpret("do");
        TextExpression help = (TextExpression) cmd;

        assertThat(help.value()).isEqualTo("Should run Do command.");
    }
    
    @Test
    public void an_alias_is_removed() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        GetAliases getAliases = (GetAliases) engine().interpret("alias remove name");
        
        assertThat(getAliases.value()).isEmpty();
        assumeAliasesAreEmpty();
    }

    //
    // Utils
    //

    private InterpreterEngine engine() {
        return new InterpreterEngine(module);
    }

    private void assumeAliasesAreEmpty() {
        assertThat(getAliases()).isEmpty();
    }

    private Aliases getAliases() {
        Expression cmd = engine().interpret("alias");
        GetAliases getAliases = (GetAliases) cmd;
        return getAliases.value();
    }

    private GivenUser givenUser(String userName) {
        return new GivenUser(module, userName);
    }

    private Alias alias() {
        return alias("name", "cmd");
    }

    private Alias alias(String name, String command) {
        return new Alias(name, command);
    }

}
