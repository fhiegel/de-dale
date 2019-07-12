package com.dedale.slack;

import com.dedale.slack.command.request.SlackRequestBuilder;
import com.dedale.slack.command.request.SlackRequestTestFactory;

public class SlackTestUtils {

    public static SlackRequestBuilder beginRequest() {
        return SlackRequestTestFactory.beginRequest();
    }


}
