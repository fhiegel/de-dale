package com.dedale.slack.client;

import java.io.InputStream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.dedale.slack.client.request.SlackRequestBuilder;
import com.dedale.slack.client.request.SlackRequestTestFactory;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.utils.JsonUtils;

public class SlackTestUtils {

    public static Form defaultSlackRequest() {
        return defaultRequest().build();
    }

    public static SlackRequestBuilder defaultRequest() {
        return SlackRequestTestFactory.defaultFullRequest();
    }

    public static SlackRequestBuilder beginRequest() {
        return SlackRequestTestFactory.beginRequest();
    }

    public static String getResponseContentAsString(Response response) throws Exception {
        InputStream inputStream = (InputStream) response.getEntity();
        return JsonUtils.asJson(inputStream, SlackResponse.class);
    }

}
