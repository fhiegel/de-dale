package com.dedale.slack.command.response;

import java.util.function.Consumer;

import com.dedale.utils.builder.AbstractFluentBuilder;

public class SlackAttachmentFieldBuilder<NB>
        extends AbstractFluentBuilder<SlackAttachmentField, NB, SlackAttachmentFieldBuilder<NB>> {

    SlackAttachmentFieldBuilder(Consumer<? super SlackAttachmentField> beanConsumer, NB nextBuilder) {
        super(defaultField(), beanConsumer, nextBuilder);
    }

    private static SlackAttachmentField defaultField() {
        return new SlackAttachmentField();
    }

    public SlackAttachmentFieldBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackAttachmentFieldBuilder<NB> withValue(String value) {
        bean.setValue(value);
        return this;
    }

    public SlackAttachmentFieldBuilder<NB> isShort() {
        bean.setShort(true);
        return this;
    }

    // Build in methods

    public NB endField() {
        return internalEndBean();
    }

}
