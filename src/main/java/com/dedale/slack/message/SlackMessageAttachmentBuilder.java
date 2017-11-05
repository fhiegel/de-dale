package com.dedale.slack.message;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import com.dedale.builder.AbstractFluentBuilder;

public class SlackMessageAttachmentBuilder<NB>
        extends AbstractFluentBuilder<SlackMessageAttachment, NB, SlackMessageAttachmentBuilder<NB>> {

    private static final String ERROR_COLOR = "#ff0000";

    SlackMessageAttachmentBuilder(Consumer<? super SlackMessageAttachment> beanConsumer, NB nextBuilder) {
        super(defaultAttachment(), beanConsumer, nextBuilder);
    }

    private static SlackMessageAttachment defaultAttachment() {
        return new SlackMessageAttachment();
    }

    public SlackMessageAttachmentBuilder<NB> withAuthorIcon(String authorIcon) {
        bean.setAuthorIcon(authorIcon);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withAuthorLink(String authorLink) {
        bean.setAuthorLink(authorLink);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withAuthorName(String authorName) {
        bean.setAuthorName(authorName);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> asError() {
        return withColor(ERROR_COLOR);
    }

    public SlackMessageAttachmentBuilder<NB> withColor(String color) {
        bean.setColor(color);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withFallback(String fallback) {
        bean.setFallback(fallback);
        return this;
    }

    public SlackMessageAttachmentFieldBuilder<SlackMessageAttachmentBuilder<NB>> addField() {
        return new SlackMessageAttachmentFieldBuilder<>(this::addField, this);
    }

    private SlackMessageAttachmentBuilder<NB> addField(SlackMessageAttachmentField field) {
        bean.addField(field);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withFields(List<SlackMessageAttachmentField> fields) {
        bean.setFields(fields);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withFooter(String footer) {
        bean.setFooter(footer);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withFooterIcon(String footerIcon) {
        bean.setFooterIcon(footerIcon);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withImageUrl(String imageUrl) {
        bean.setImageUrl(imageUrl);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> markdownInText() {
        return markdownIn("text");
    }

    private SlackMessageAttachmentBuilder<NB> markdownIn(String field) {
        bean.addMarkdownField(field);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withPretext(String pretext) {
        bean.setPretext(pretext);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withMarkdownText(String text) {
        return withText(text).markdownInText();
    }

    public SlackMessageAttachmentBuilder<NB> withText(String text) {
        bean.setText(text);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withThumbUrl(String thumbUrl) {
        bean.setThumbUrl(thumbUrl);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withTitleLink(String titleLink) {
        bean.setTitleLink(titleLink);
        return this;
    }

    public SlackMessageAttachmentBuilder<NB> withTimestamp(Date timestamp) {
        bean.setTimestamp(timestamp);
        return this;
    }

    // Build in methods

    public NB endAttachment() {
        return internalEndBean();
    }

}
