package com.dedale.cmd.aliases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.core.CommandDefinitions;
import com.dedale.core.ExecutionContext;
import com.dedale.core.InterpreterEngine;
import com.dedale.core.expression.Expression;
import com.dedale.core.expression.TextExpression;

public class AliasingFeatures {

    private final Aliasing module = new Aliasing();
    private InterpreterEngine engine = new InterpreterEngine(module);
    private ExecutionContext context = ExecutionContext.from(engine);

    @Test
    public void aliases_are_empty() throws Exception {
        Expression cmd = engine.interpret(context, "alias");
        Expression result = cmd.execute(context);

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(GetAliases.class);
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_one() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");

        Expression cmd = engine.interpret(context, "alias");
        Aliases aliases = ((GetAliases) cmd.execute(context)).value();

        assertThat(aliases).hasSize(1);
        assertThat(aliases).containsExactly(alias());
    }

    @Test
    public void aliases_are_not_empty_when_an_user_defined_some() throws Exception {
        givenUser("anUser").hasAlias("name", "cmd");
        givenUser("anUser").hasAlias("name2", "cmd2");
        givenUser("anUser").hasAlias("name3", "cmd3");

        Expression cmd = engine.interpret(context, "alias");
        Aliases aliases = ((GetAliases) cmd.execute(context)).value();

        assertThat(aliases).hasSize(3);
        assertThat(aliases).containsExactly(alias(), alias("name2", "cmd2"), alias("name3", "cmd3"));
    }

    @Test
    public void an_alias_is_added() throws Exception {
        assumeAliasesAreEmpty();

        Expression cmd = engine.interpret(context, "alias add name=cmd");
        cmd.execute(context);

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void an_alias_is_added_without_quotes() throws Exception {
        assumeAliasesAreEmpty();

        Expression cmd = engine.interpret(context, "alias add name=\"cmd\"");
        cmd.execute(context);

        assertThat(getAliases()).containsExactly(alias());
    }

    @Test
    public void print_help() throws Exception {
        Expression cmd = engine.interpret(context, "alias --help");

        TextExpression help = (TextExpression) cmd.execute(context);
        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void an_alias_is_run() throws Exception {
        assumeAliasesAreEmpty();

        Expression cmd = engine.interpret(context, "alias add doHelp=\"alias --help\"");
        cmd.execute(context);

        cmd = engine.interpret(context, "doHelp");
        TextExpression help = (TextExpression) cmd.execute(context);

        assertThat(help.value()).isEqualTo("Help text");
    }

    @Test
    public void command_is_run_when_alias_with_same_name_definer() throws Exception {
        CommandDefinitions commands = CommandDefinitions
                .defineCommands()
                .withCommand("doSomethingElse", new TextExpression("Text when Do Something Else"))
                .withCommand("do", new TextExpression("Should run Do command."));

        engine = new InterpreterEngine(new Aliasing(c -> commands));
        context = ExecutionContext.from(engine);

        Expression cmd = engine.interpret(context, "alias add do=\"doSomethingElse\"");
        cmd.execute(context);

        cmd = engine.interpret(context, "do");
        TextExpression help = (TextExpression) cmd.execute(context);

        assertThat(help.value()).isEqualTo("Should run Do command.");
    }

    //
    // Utils
    //

    private void assumeAliasesAreEmpty() {
        assertThat(getAliases()).isEmpty();
    }

    private Aliases getAliases() {
        Expression cmd = engine.interpret(context, "alias");
        GetAliases getAliases = (GetAliases) cmd.execute(context);
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
