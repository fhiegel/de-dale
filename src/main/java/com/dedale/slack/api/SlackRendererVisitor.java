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

    private SlackRendererVisitor(SlackMessageBuilder messageBuilder) {
        super(messageBuilder);
    }

    public SlackRendererVisitor(ExecutionContext context) {
        this(SlackMessageBuilder.beginMessage());
        this
                .when(GetAliases.class, e -> result.ephemeralResponse().withText(aliasMarkdown(e)))
                .otherwise(e -> result
                        .inChannel()
                        .addAttachment()
                        .withAuthorName(context.user().name())
                        .withMarkdownText((context.input().isEmpty() ? "" : context.input() + "= ") + BOLD + print(e) + BOLD)
                        .endAttachment());
    }

    private Markdown aliasMarkdown(GetAliases e) {
        Markdown markdown = new Markdown();
        markdown = markdown.bold().append("Aliases : ").bold().line();
        for (Alias alias : e.value()) {
            markdown = markdown.quote().append(alias.name()).append(" = ").append(alias.commandLine()).line();
        }
        return markdown;
    }

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SlackRendererVisitor visitor(SlackMessageBuilder result) {
        return new SlackRendererVisitor(result);
    }

    public SlackMessageBuilder builder() {
        return result;
    }

    public SlackMessage message() {
        return result.build();
    }

}
