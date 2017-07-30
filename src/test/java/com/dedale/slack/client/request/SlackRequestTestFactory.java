package com.dedale.slack.client.request;

public class SlackRequestTestFactory {

    public static SlackRequestBuilder beginRequest() {
        return new SlackRequestBuilder();
    }

    public static SlackRequestBuilder defaultFullRequest() {
        return beginRequest()
                .withText("1+2")
                .withChannelId("C2147483705")
                .withChannelName("test")
                .withCommand("/weather")
                .withResponseUrl("https://hooks.slack.com/commands/1234/5678")
                .withTeamDomain("example")
                .withTeamId("T0001")
                .withToken("GCAgTZ41iWJxG67LqN9IIDmU")
                .withUserId("U2147483697")
                .withUserName("Steve");
    }
}
