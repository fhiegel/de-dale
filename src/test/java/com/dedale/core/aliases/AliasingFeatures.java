package com.dedale.core.aliases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.core.User;
import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.TextExpression;

public class AliasingFeatures {

    private UserAliases userAliases = new InMemoryUserAliases();
    private Aliasing module = new Aliasing(CommandModule.EMPTY, userAliases);
    private InterpreterEngine engine = new InterpreterEngine(module);
    private ExecutionContext context = engine.defaultContext().withUser("anUser");;

    @Test
    public void aliases_are_empty() throws Exception {
        Expression result = defaultInterpret("alias");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(GetAliases.class);
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_one() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        Expression cmd = defaultInterpret("alias");
        Aliases aliases = ((GetAliases) cmd).value();

        assertThat(aliases).hasSize(1);
        assertThat(aliases).containsExactly(alias());
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_some() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");
        givenUser("anUser").hasAlias("name2", "cmd2");
        givenUser("anUser").hasAlias("name3", "cmd3");

        Expression cmd = defaultInterpret("alias");
        Aliases aliases = ((GetAliases) cmd).value();

        assertThat(aliases).hasSize(3);
        assertThat(aliases).containsExactly(alias(), alias("name2", "cmd2"), alias("name3", "cmd3"));
    }

    @Test
    public void aliases_are_ordered_by_name() throws Exception {
        givenUser("anUser").hasAlias("name3", "cmd3");
        givenUser("anUser").hasAlias("name", "cmd");
        givenUser("anUser").hasAlias("name2", "cmd2");

        Expression cmd = defaultInterpret("alias");
        Aliases aliases = ((GetAliases) cmd).value();

        assertThat(aliases).hasSize(3);
        assertThat(aliases).containsExactly(alias(), alias("name2", "cmd2"), alias("name3", "cmd3"));
    }

    @Test
    public void an_alias_is_added() throws Exception {
        assumeAliasesAreEmpty();

        defaultInterpret("alias add name=cmd");

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void an_alias_is_added_without_quotes() throws Exception {
        assumeAliasesAreEmpty();

        defaultInterpret("alias add name=\"cmd\"");

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void print_help() throws Exception {
        Expression cmd = defaultInterpret("alias --help");

        TextExpression help = (TextExpression) cmd;
        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void an_alias_is_run() throws Exception {
        assumeAliasesAreEmpty();

        Expression cmd = defaultInterpret("alias add doHelp=\"alias --help\"");

        cmd = defaultInterpret("doHelp");
        TextExpression help = (TextExpression) cmd;

        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void command_is_run_when_alias_with_same_name_definer() throws Exception {
        module = new Aliasing(CommandDefinitions
                .defineCommands()
                .withCommand("doSomethingElse", new TextExpression("Text when Do Something Else"))
                .withCommand("do", new TextExpression("Should run Do command.")), userAliases);
        engine = new InterpreterEngine(module);
        context = engine.defaultContext();
        
        Expression cmd = defaultInterpret("alias add do=\"doSomethingElse\"");

        cmd = defaultInterpret("do");
        TextExpression help = (TextExpression) cmd;

        assertThat(help.value()).isEqualTo("Should run Do command.");
    }

    @Test
    public void an_alias_is_removed() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        GetAliases getAliases = (GetAliases) defaultInterpret("alias remove name");

        assertThat(getAliases.value()).isEmpty();
        assumeAliasesAreEmpty();
    }

    @Test
    public void an_alias_is_unique() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        GetAliases getAliases = (GetAliases) defaultInterpret("alias add name=something");

        assertThat(getAliases.value()).containsExactly(alias("name", "something"));
    }

    @Test
    public void an_alias_is_for_an_user() throws Exception {
        givenUser("anOtherUser").hasAlias("name", "cmd");

        context = context.withUser("anUser").withInput("alias");
        GetAliases getAliases = (GetAliases) engine.interpret(context);
        assertThat(getAliases.value()).isEmpty();
    }

    @Test
    public void an_alias_is_for_an_ussdgsgsdfer() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");
        givenUser("anOtherUser").hasAlias("name", "cmd for another user");

        context = context.withUser("anUser").withInput("alias");
        GetAliases getAliases = (GetAliases) engine.interpret(context);
        assertThat(getAliases.value()).containsExactly(alias("name", "cmd"));

        context = context.withUser("anOtherUser").withInput("alias");
        getAliases = (GetAliases) engine.interpret(context);
        assertThat(getAliases.value()).containsExactly(alias("name", "cmd for another user"));
    }

    //
    // Utils
    //

    private Expression defaultInterpret(String input) {
        return engine.interpret(context.withInput(input));
    }

    private void assumeAliasesAreEmpty() {
        assertThat(getAliases()).isEmpty();
    }

    private Aliases getAliases() {
        Expression cmd = defaultInterpret("alias");
        GetAliases getAliases = (GetAliases) cmd;
        return getAliases.value();
    }

    private GivenUser givenUser(String userName) {
        return new GivenUser(userAliases, new User(userName));
    }

    private Alias alias() {
        return alias("name", "cmd");
    }

    private Alias alias(String name, String command) {
        return new Alias(name, command);
    }

}
