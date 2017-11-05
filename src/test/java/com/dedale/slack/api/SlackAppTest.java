package com.dedale.slack.api;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.dedale.DeDaleResourceConfig;
import com.dedale.slack.SlackTestUtils;
import com.dedale.utils.resources.Resources;

public class SlackAppTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new DeDaleResourceConfig();
    }
    
    @Test
    public void challenge_accepted() throws Exception {
        String challenge = Resources.getRelativeTo(getClass(), "challenge.json").asString();
        
        Response responseMsg = target("slack").request().post(Entity.json(challenge));

        assertThat(responseMsg.readEntity(String.class)).containsPattern("\"challenge\": \"\\S+\"");
    }
    
    @Test
    public void command_invoked() throws Exception {
        Form commandLine = SlackTestUtils.beginRequest().withText("command line").build();
        
        Response responseMsg = target("slack/cmd").request().post(Entity.form(commandLine));
        
        assertThat(responseMsg.readEntity(String.class)).containsPattern("\"text\":\".*command line.*\"");
    }

    
}
