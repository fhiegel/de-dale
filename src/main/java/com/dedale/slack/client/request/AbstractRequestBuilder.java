package com.dedale.slack.client.request;

import io.micronaut.http.client.RxHttpClient;

import java.util.function.Consumer;

public abstract class AbstractRequestBuilder<CurrentBuilder extends AbstractRequestBuilder<CurrentBuilder>> {

    protected final RxHttpClient client;
    protected final SlackClientRequest request;

    AbstractRequestBuilder(AbstractRequestBuilder<?> previous) {
        this(previous.client, previous.request);
    }

    AbstractRequestBuilder(RxHttpClient client, SlackClientRequest request) {
        this.client = client;
        this.request = request;
    }

    public CurrentBuilder appendToRequest(Consumer<SlackClientRequest> consumer) {
        consumer.accept(request);
        return (CurrentBuilder) this;
    }

}
