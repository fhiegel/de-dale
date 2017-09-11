package com.dedale.hermes;

import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

public class HermesResponse {

    private HermesDiceResult response;

    public boolean isValid() {
        // TODO Auto-generated method stub
        return false;
    }

    public void addResponse(HermesDiceResult item) {
        response = item;
    }

    /**
     * @deprecated Devrait être portée sur un autre objet
     * @param slackResponse
     * @return
     */
    @Deprecated
    public SlackResponse accept(SlackResponseBuilder slackResponse) {
        if (response.isValid()) {
            slackResponse.addAttachment().withAuthorName(response.getAuthor()).withMarkdownText(response.getText())
                    .endAttachment();
        } else {
            slackResponse.addAttachment().asError().withAuthorName(response.getAuthor())
                    .withMarkdownText(response.getText()).endAttachment();
        }
        return slackResponse.inChannel().build();
    }

}
