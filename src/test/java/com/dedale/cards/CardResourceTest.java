package com.dedale.cards;

import static com.dedale.cards.CardResource.PATH;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.dedale.DeDaleResourceConfig;
import com.dedale.utils.FileTestUtils;
import com.dedale.utils.JsonUtils;

public class CardResourceTest extends JerseyTest {
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Override
    protected Application configure() {
        return new DeDaleResourceConfig();
    }
    
    @Test
    public void should_return_200_for_getting_all_cards() throws Exception {
        Response response = target(PATH + "/all").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_getting_all_cards_equals_file() throws Exception {
        Response response = target(PATH + "/all").request().get();
        assertThat(response.readEntity(String.class)).isEqualTo(getFileAsJson("cards_all.json"));
    }
    
    @Test
    public void should_return_200_for_getting_single_card_with_path() throws Exception {
        Response response = target(PATH + "/12").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_getting_single_card_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12").request().get();
        assertThat(response.readEntity(String.class)).isEqualTo(getFileAsJson("single_card.json"));
    }
    
    @Test
    public void should_return_200_for_getting_multiple_cards_with_path() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
    }
    
    @Test
    public void should_getting_multiple_cards_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        assertThat(response.readEntity(String.class)).isEqualTo(getFileAsJson("multiple_cards.json"));
    }
    
    private String getFileAsJson(String filePath) {
        return FileTestUtils.getResourceFileAsJson(getClass(), filePath);
    }
    
}
