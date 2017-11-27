package com.dedale.slack.api;

import static com.dedale.markdown.MarkdownTags.BOLD;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;

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
    public SlackMessage slackRoll(SlackRequest request) {
        if (request == null) {
            return SlackMessageBuilder.beginMessage().withText("ERROR").build();
        }
        log.info(request.toString());
        // String diceResult = calculator.calculate(diceSentence).toString();

        String responseText = request.getText() + "= " + BOLD + "ok" + BOLD;

        return SlackMessageBuilder.beginMessage().withText(responseText).build();
    }
}
