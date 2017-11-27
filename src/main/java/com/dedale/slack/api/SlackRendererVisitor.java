package com.dedale.slack.api;

import static com.dedale.markdown.MarkdownTags.BOLD;

import com.dedale.core.aliases.Alias;
import com.dedale.core.aliases.GetAliases;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionPrinter;
import com.dedale.core.engine.expression.ExpressionVisitor;
import com.dedale.markdown.Markdown;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;

public class SlackRendererVisitor extends ExpressionVisitor<SlackMessageBuilder> {

    private SlackMessageBuilder builder = SlackMessageBuilder.beginMessage();

    public SlackRendererVisitor(ExecutionContext context) {
        this
                .when(GetAliases.class, e -> builder.ephemeralResponse().withText(aliasMarkdown(e)))
                .otherwise(e -> builder
                        .inChannel()
                        .addAttachment()
                        .withAuthorName(context.user().name())
                        .withMarkdownText((context.input().isEmpty() ? "" :context.input() + "= ") + BOLD + print(e) + BOLD)
                        .endAttachment());
    }

    private Markdown aliasMarkdown(GetAliases e) {
        Markdown markdown = new Markdown();
        for (Alias alias : e.value()) {
            markdown = markdown.append(alias.name()).append(" = ").append(alias.commandLine()).line();
        }
        return markdown;
    }

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

    public SlackMessageBuilder builder() {
        return builder;
    }

    public SlackMessage message() {
        return builder.build();
    }

}
