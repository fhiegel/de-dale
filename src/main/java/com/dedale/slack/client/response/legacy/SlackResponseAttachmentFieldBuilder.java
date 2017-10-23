package com.dedale.slack.client.response.legacy;

import java.util.function.Consumer;

import com.dedale.builder.AbstractFluentBuilder;

public class SlackResponseAttachmentFieldBuilder<NB>
        extends AbstractFluentBuilder<SlackResponseAttachmentField, NB, SlackResponseAttachmentFieldBuilder<NB>> {

    SlackResponseAttachmentFieldBuilder(Consumer<? super SlackResponseAttachmentField> beanConsumer,
            NB nextBuilder) {
        super(defaultField(), beanConsumer, nextBuilder);
    }

    private static SlackResponseAttachmentField defaultField() {
        return new SlackResponseAttachmentField();
    }

    public SlackResponseAttachmentFieldBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackResponseAttachmentFieldBuilder<NB> withValue(String value) {
        bean.setValue(value);
        return this;
    }

    public SlackResponseAttachmentFieldBuilder<NB> isShort() {
        bean.setShort(true);
        return this;
    }

    // Build in methods

    public NB endField() {
        return internalEndBean();
    }

}
