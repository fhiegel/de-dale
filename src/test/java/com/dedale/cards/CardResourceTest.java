package com.dedale.cards;

import static com.dedale.cards.CardResource.PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.dedale.utils.FileTestUtils;
import com.dedale.utils.jersey.ApplicationRule;

public class CardResourceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private AddCardCommandHandler addCard;

    @Rule
    public ApplicationRule jersey = ApplicationRule
            .dedale()
            .configureBinding(binder -> binder.bind(addCard).to(AddCardCommandHandler.class));

    @Test
    public void should_return_200_for_getting_all_cards() throws Exception {
        Response response = target(PATH + "/all").request().get();
        responseHasStatus(response);
    }

    @Test
    public void should_getting_all_cards_equals_file() throws Exception {
        Response response = target(PATH + "/all").request().get();
        assertResponseEqualsFile(response, "container_empty.json");
    }

    @Test
    public void should_return_200_for_getting_single_card_with_path() throws Exception {
        Response response = target(PATH + "/12").request().get();
        responseHasStatus(response);
    }

    @Test
    public void should_getting_single_card_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12").request().get();
        assertResponseEqualsFile(response, "container_with_single_card.json");
    }

    @Test
    public void should_return_200_for_getting_multiple_cards_with_path() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        responseHasStatus(response);
    }

    @Test
    public void should_getting_multiple_cards_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        assertResponseEqualsFile(response, "container_with_multiple_cards.json");
    }

    @Test
    public void should_return_200_when_add_a_card() throws Exception {
        when(addCard.handle(any(AddCardCommand.class))).thenReturn(new Card());

        Response response = target(PATH).request().post(Entity.json(new Card()));
        responseHasStatus(response);
    }

    @Test
    public void should_getting_card_with_id_when_add_a_card() throws Exception {
        givenNextCardIdIs(12);

        Response response = target(PATH).request().post(Entity.json(new Card()));

        assertResponseEqualsFile(response, "single_card.json");
        verify(addCard, times(1)).handle(any(AddCardCommand.class));
    }

    //
    // Utils
    //

    private WebTarget target(String path) {
        return jersey.target(path);
    }

    private void responseHasStatus(Response response) {
        assertThat(response.getStatus()).as(response.readEntity(String.class)).isEqualTo(200);
    }

    private void assertResponseEqualsFile(Response response, String fileName) {
        assertThat(response.readEntity(String.class)).isEqualTo(getFileAsJson(fileName));
    }

    private void givenNextCardIdIs(long nextCardId) {
        when(addCard.handle(any(AddCardCommand.class))).thenReturn(new Card(nextCardId));
    }

    private String getFileAsJson(String filePath) {
        return FileTestUtils.getResourceFileAsJson(getClass(), filePath);
    }

}
