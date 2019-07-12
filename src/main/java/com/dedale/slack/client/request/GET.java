package com.dedale.slack.client.request;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;

import java.net.URI;

import static io.micronaut.http.HttpRequest.GET;

public class GET extends AbstractRequestBuilder<GET> {

    private final String method;

    GET(AbstractRequestBuilder<?> previous, String method) {
        super(previous);
        this.method = method;
    }

    public Flowable<String> get() {
        return client.retrieve(HttpRequest.GET(uri(method, request))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }

    private static URI uri(String method, SlackClientRequest requestBuilder) {
        UriBuilder uriBuilder = UriBuilder.of(method);
        requestBuilder.asMap().forEach(uriBuilder::queryParam);
        return uriBuilder.build();
    }
}