package com.dedale.slack.client;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dedale.slack.client.request.SlackRequest;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

@Path("slack")
public class SlackApp {

    private static final Logger log = Logger.getLogger(SlackApp.class.getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listen(String request) {
        log.info(request);
        return request;
    }

    @POST
    @Path("cmd")
    public SlackResponse slackRoll(SlackRequest slackRequest) {
        if (slackRequest == null) {
            return SlackResponseBuilder.beginResponse().withText("ERROR").build();
        }
        // String diceResult = calculator.calculate(diceSentence).toString();

        String responseText = slackRequest.getText() + "= " + MARKDOWN_BOLD + "ok" + MARKDOWN_BOLD;

        return SlackResponseBuilder.beginResponse().withText(responseText).build();
    }
}
