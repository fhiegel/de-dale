package com.dedale.cards;

import static com.dedale.cards.CardResource.PATH;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.dedale.DeDaleResourceConfig;
import com.dedale.utils.FileTestUtils;

public class CardResourceTest extends JerseyTest {
    
    @Override
    protected Application configure() {
        return new DeDaleResourceConfig();
    }
    
    @Test
    public void should_return_200_for_getting_all_cards() throws Exception {
        Response responseMsg = target(PATH).request().get();
        assertThat(responseMsg.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_getting_all_cards_equals_file() throws Exception {
        Response responseMsg = target(PATH).request().get();
        assertThat(responseMsg.readEntity(String.class)).isEqualTo(getFileAsJson("cards_all.json"));
    }
    
    private String getFileAsJson(String filePath) {
        return FileTestUtils.getResourceFileAsJson(getClass(), filePath);
    }
    
}
