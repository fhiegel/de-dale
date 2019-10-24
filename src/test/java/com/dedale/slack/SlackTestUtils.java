package com.dedale.slack;

import com.dedale.slack.server.command.request.SlackCommandRequestBuilder;
import com.dedale.slack.server.command.request.SlackRequestTestFactory;

public class SlackTestUtils {

    public static SlackCommandRequestBuilder beginRequest() {
        return SlackRequestTestFactory.beginRequest();
    }


}
