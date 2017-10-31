package com.dedale.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.dedale.DeDaleResourceConfig;
import com.dedale.slack.client.SlackTestUtils;
import com.dedale.slack.client.request.SlackRequestTestFactory;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.utils.FileTestUtils;
import com.dedale.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DiceResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new DeDaleResourceConfig();
    }

    @Test
    public void should_return_200_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "1+1";

        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));

        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_return_appropriate_result_for_well_formed_dice_query() throws Exception {
        // Given
        String wellFormedQuery = "1+1";

        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));

        // Then
        assertThat(responseMsg.readEntity(String.class)).isEqualTo("2");
    }
    
    @Test
    public void should_roll_a_dice_twice() throws Exception {
        // Given
        String wellFormedQuery = "1+2 3+4";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.readEntity(String.class)).isEqualTo("3 7");
    }
    
    @Test
    public void should_roll_a_dice_twice_despite_spaces() throws Exception {
        // Given
        String wellFormedQuery = "1+ 2 3+ 4";
        
        // When
        Response responseMsg = target("dices").request().post(Entity.text(wellFormedQuery));
        
        // Then
        assertThat(responseMsg.readEntity(String.class)).isEqualTo("3 7");
    }

    @Test
    public void should_return_400_for_malformed_dice_query() throws Exception {
        // Given
        String malFormedQuery = "malFormedQuery";

        // When
        Response responseMsg = target("dices").request().post(Entity.text(malFormedQuery));

        // Then
        assertThat(responseMsg.getStatus()).isEqualTo(400);
    }

    @Test
    public void should_return_200_for_a_correct_slack_input() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        // When
        Response response = target("dices/slack").request().post(Entity.form(form));

        // Then
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_return_expected_response_for_a_correct_slack_input() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        // When
        Response response = target("dices/slack").request().post(Entity.form(form));

        // Then
        String responseContent = getResponseContentAsString(response);
        String expectedFileContent = getFileAsString("dice_slack_response.json");

        assertThat(responseContent).isEqualTo(expectedFileContent);
    }

    @Test
    public void should_return_expected_response_for_a_invalid_slack_input() throws Exception {
        // Given
        Form form = SlackRequestTestFactory.beginRequest().withUserName("Dummy User").withText("not a dice Sentense")
                .build();

        // When
        Response response = target("dices/slack").request().post(Entity.form(form));

        // Then
        String responseContent = getResponseContentAsString(response);
        String expectedFileContent = getFileAsString("dice_slack_response_for_invalid_request.json");

        assertThat(responseContent).isEqualTo(expectedFileContent);
    }

    //
    // Utilitaires
    //

    private String getFileAsString(String filePath) {
        File file = FileTestUtils.getResourceFile(getClass(), filePath);
        return JsonUtils.asJson(file);
    }

    private String getResponseContentAsString(Response response)
            throws JsonParseException, JsonMappingException, IOException {
        InputStream inputStream = (InputStream) response.getEntity();
        return JsonUtils.asJson(inputStream, SlackResponse.class);
    }

}
