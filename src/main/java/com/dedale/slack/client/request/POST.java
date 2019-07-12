package com.dedale.slack.client.request;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.reactivex.Flowable;

import static io.micronaut.http.HttpRequest.POST;

public class POST extends AbstractRequestBuilder<POST> {

    private final String method;

    POST(AbstractRequestBuilder<?> previous, String method) {
        super(previous);
        this.method = method;
    }

    public Flowable<HttpResponse<String>> post() {
        return client.exchange(POST(method, request.asMap())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED), String.class);
    }

}