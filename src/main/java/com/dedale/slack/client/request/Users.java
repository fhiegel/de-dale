package com.dedale.slack.client.request;

public class Users extends AbstractRequestMethod<Users> {

    public Users(AbstractRequestBuilder<?> previous) {
        super(previous, "users");
    }

    public GET list() {
        return getWithMethod("list");
    }

    public GET conversations() {
        return getWithMethod("conversations");
    }


}
