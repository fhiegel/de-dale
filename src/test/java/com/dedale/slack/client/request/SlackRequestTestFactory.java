package com.dedale.slack.client.request;

import javax.ws.rs.core.Form;

public class SlackRequestTestFactory {

    public static Form defaultSlackRequest(){
        Form form = new Form();
        form.param(SlackRequest.TEXT_PARAM, "1+2");
        form.param(SlackRequest.CHANNEL_ID_PARAM, "C2147483705");
        form.param(SlackRequest.CHANNEL_NAME_PARAM, "test");
        form.param(SlackRequest.COMMAND_PARAM, "/weather");
        form.param(SlackRequest.RESPONSE_URL_PARAM, "https://hooks.slack.com/commands/1234/5678");
        form.param(SlackRequest.TEAM_DOMAIN_PARAM, "example");
        form.param(SlackRequest.TEAM_ID_PARAM, "T0001");
        form.param(SlackRequest.TOKEN_PARAM, "GCAgTZ41iWJxG67LqN9IIDmU");
        form.param(SlackRequest.USER_ID_PARAM, "U2147483697");
        form.param(SlackRequest.USER_NAME_PARAM, "Steve");
        return form;
    }
}
