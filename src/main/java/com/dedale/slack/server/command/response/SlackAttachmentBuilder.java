package com.dedale.slack.server.command.response;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class SlackAttachmentBuilder<NB>
        extends AbstractFluentBuilder<SlackAttachment, NB, SlackAttachmentBuilder<NB>> {

    private static final String ERROR_COLOR = "#ff0000";

    SlackAttachmentBuilder(Consumer<? super SlackAttachment> beanConsumer, NB nextBuilder) {
        super(defaultAttachment(), beanConsumer, nextBuilder);
    }

    private static SlackAttachment defaultAttachment() {
        return new SlackAttachment();
    }

    public SlackAttachmentBuilder<NB> withAuthorIcon(String authorIcon) {
        bean.setAuthorIcon(authorIcon);
        return this;
    }

    public SlackAttachmentBuilder<NB> withAuthorLink(String authorLink) {
        bean.setAuthorLink(authorLink);
        return this;
    }

    public SlackAttachmentBuilder<NB> withAuthorName(String authorName) {
        bean.setAuthorName(authorName);
        return this;
    }

    public SlackAttachmentBuilder<NB> asError() {
        return withColor(ERROR_COLOR);
    }

    public SlackAttachmentBuilder<NB> withColor(String color) {
        bean.setColor(color);
        return this;
    }

    public SlackAttachmentBuilder<NB> withFallback(String fallback) {
        bean.setFallback(fallback);
        return this;
    }

    public SlackAttachmentFieldBuilder<SlackAttachmentBuilder<NB>> addField() {
        return new SlackAttachmentFieldBuilder<>(this::addField, this);
    }

    private SlackAttachmentBuilder<NB> addField(SlackAttachmentField field) {
        bean.addField(field);
        return this;
    }

    public SlackAttachmentBuilder<NB> withFields(List<SlackAttachmentField> fields) {
        bean.setFields(fields);
        return this;
    }

    public SlackAttachmentBuilder<NB> withFooter(String footer) {
        bean.setFooter(footer);
        return this;
    }

    public SlackAttachmentBuilder<NB> withFooterIcon(String footerIcon) {
        bean.setFooterIcon(footerIcon);
        return this;
    }

    public SlackAttachmentBuilder<NB> withImageUrl(String imageUrl) {
        bean.setImageUrl(imageUrl);
        return this;
    }

    public SlackAttachmentBuilder<NB> markdownInText() {
        return markdownIn("text");
    }

    public SlackAttachmentBuilder<NB> markdownInPretext() {
        return markdownIn("pretext");
    }

    private SlackAttachmentBuilder<NB> markdownIn(String field) {
        bean.addMarkdownField(field);
        return this;
    }

    public SlackAttachmentBuilder<NB> withPretext(String pretext) {
        bean.setPretext(pretext);
        return this;
    }

    public SlackAttachmentBuilder<NB> withMarkdownText(Object text) {
        return withMarkdownText(text.toString());
    }
    
    public SlackAttachmentBuilder<NB> withMarkdownText(String text) {
        return withText(text).markdownInText();
    }

    public SlackAttachmentBuilder<NB> withText(String text) {
        bean.setText(text);
        return this;
    }

    public SlackAttachmentBuilder<NB> withThumbUrl(String thumbUrl) {
        bean.setThumbUrl(thumbUrl);
        return this;
    }

    public SlackAttachmentBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackAttachmentBuilder<NB> withTitleLink(String titleLink) {
        bean.setTitleLink(titleLink);
        return this;
    }

    public SlackAttachmentBuilder<NB> withTimestamp(Date timestamp) {
        bean.setTimestamp(timestamp);
        return this;
    }

    // Build in methods

    public NB endAttachment() {
        return internalEndBean();
    }

}
