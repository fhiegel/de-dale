package com.dedale.slack.client.request;

public class Conversations extends AbstractRequestMethod<Conversations> {

    public Conversations(AbstractRequestBuilder<?> previous) {
        super(previous, "conversations");
    }

    public GET list() {
        return getWithMethod("list");
    }

}