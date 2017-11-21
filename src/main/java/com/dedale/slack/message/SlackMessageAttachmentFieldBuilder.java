package com.dedale.slack.message;

import java.util.function.Consumer;

import com.dedale.builder.AbstractFluentBuilder;

public class SlackMessageAttachmentFieldBuilder<NB>
        extends AbstractFluentBuilder<SlackMessageAttachmentField, NB, SlackMessageAttachmentFieldBuilder<NB>> {

    SlackMessageAttachmentFieldBuilder(Consumer<? super SlackMessageAttachmentField> beanConsumer, NB nextBuilder) {
        super(defaultField(), beanConsumer, nextBuilder);
    }

    private static SlackMessageAttachmentField defaultField() {
        return new SlackMessageAttachmentField();
    }

    public SlackMessageAttachmentFieldBuilder<NB> withTitle(String title) {
        bean.setTitle(title);
        return this;
    }

    public SlackMessageAttachmentFieldBuilder<NB> withValue(String value) {
        bean.setValue(value);
        return this;
    }

    public SlackMessageAttachmentFieldBuilder<NB> isShort() {
        bean.setShort(true);
        return this;
    }

    // Build in methods

    public NB endField() {
        return internalEndBean();
    }

}
