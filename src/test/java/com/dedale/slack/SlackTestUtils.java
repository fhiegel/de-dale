package com.dedale.slack;

import com.dedale.slack.request.SlackRequestBuilder;
import com.dedale.slack.request.SlackRequestTestFactory;

public class SlackTestUtils {

    public static SlackRequestBuilder.Form defaultSlackRequest() {
        return defaultRequest().build();
    }

    static SlackRequestBuilder defaultRequest() {
        return SlackRequestTestFactory.defaultFullRequest();
    }

    public static SlackRequestBuilder beginRequest() {
        return SlackRequestTestFactory.beginRequest();
    }


}
