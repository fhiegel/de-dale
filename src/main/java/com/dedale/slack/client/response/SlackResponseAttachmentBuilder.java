package com.dedale.slack.client.response;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import com.dedale.builder.AbstractFluentBuilder;

public class SlackResponseAttachmentBuilder<NB>
        extends AbstractFluentBuilder<SlackResponseAttachment, NB, SlackResponseAttachmentBuilder<NB>> {

    private static final String ERROR_COLOR = "#ff0000";

    SlackResponseAttachmentBuilder(Consumer<? super SlackResponseAttachment> beanConsumer, NB nextBuilder) {
        super(defaultAttachment(), beanConsumer, nextBuilder);
    }

    private static SlackResponseAttachment defaultAttachment() {
        return new SlackResponseAttachment();
    }

    public SlackResponseAttachmentBuilder<NB> withAuthorIcon(String authorIcon) {
        bean.setAuthorIcon(authorIcon);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withAuthorLink(String authorLink) {
        bean.setAuthorLink(authorLink);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withAuthorName(String authorName) {
        bean.setAuthorName(authorName);
        return this;
    }
    

    public SlackResponseAttachmentBuilder<NB> asError() {
        return withColor(ERROR_COLOR);
    }

    public SlackResponseAttachmentBuilder<NB> withColor(String color) {
        bean.setColor(color);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withFallback(String fallback) {
        bean.setFallback(fallback);
        return this;
    }

    public SlackResponseAttachmentFieldBuilder<SlackResponseAttachmentBuilder<NB>> addField() {
        return new SlackResponseAttachmentFieldBuilder<>(this::addField, this);
    }

    private SlackResponseAttachmentBuilder<NB> addField(SlackResponseAttachmentField field) {
        bean.addField(field);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withFields(List<SlackResponseAttachmentField> fields) {
        bean.setFields(fields);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withFooter(String footer) {
        bean.setFooter(footer);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withFooterIcon(String footerIcon) {
        bean.setFooterIcon(footerIcon);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withImageUrl(String imageUrl) {
        bean.setImageUrl(imageUrl);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> markdownInText() {
        return markdownIn("text");
    }

    private SlackResponseAttachmentBuilder<NB> markdownIn(String field) {
        bean.addMarkdownField(field);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withPretext(String pretext) {
        bean.setPretext(pretext);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withMarkdownText(String text) {
        return withText(text).markdownInText();
    }

    public SlackResponseAttachmentBuilder<NB> withText(String text) {
        bean.setText(text);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withThumbUrl(String thumbUrl) {
        bean.setThumbUrl(thumbUrl);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withTitleLink(String titleLink) {
        bean.setTitleLink(titleLink);
        return this;
    }

    public SlackResponseAttachmentBuilder<NB> withTimestamp(Date timestamp) {
        bean.setTimestamp(timestamp);
        return this;
    }

    // Build in methods

    public NB endAttachment() {
        return internalEndBean();
    }

}
