package com.dedale.slack.client.request;

abstract class AbstractRequestMethod<CurrentBuilder extends AbstractRequestMethod<CurrentBuilder>>
        extends AbstractRequestBuilder<CurrentBuilder> {

    private final String method;

    AbstractRequestMethod(AbstractRequestBuilder<?> previous, String method) {
        super(previous);
        this.method = method;
    }

    final String appendMethod(String method) {
        return String.join(".", this.method, method);
    }

    final GET getWithMethod(String method) {
        return new GET(this, appendMethod(method));
    }

    final POST postWithMethod(String method) {
        return new POST(this, appendMethod(method));
    }

}
