package com.dedale.cards;

import static com.dedale.cards.CardResource.PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.dedale.cards.query.AddCardCommand;
import com.dedale.cards.query.AddCardCommandHandler;
import com.dedale.utils.FileTestUtils;
import com.dedale.utils.jersey.ApplicationRule;

public class CardResourceTest {

    private CardRepository repository = spy(new CardRepositoryInMemory());

    private AddCardCommandHandler addCard = spy(new AddCardCommandHandler(repository));

    @Rule
    public ApplicationRule jersey = ApplicationRule.dedale().configureBinding(binder -> {
        binder.bind(addCard).to(AddCardCommandHandler.class);
        binder.bind(repository).to(CardRepository.class);
    });

    @Before
    public void beforeName() throws Exception {
        repository.add(Card.builder().id(12).title("Titre 12").build());
        repository.add(Card.builder().id(14).description("Description 14").build());
    }

    @Test
    public void should_return_200_for_getting_all_cards() throws Exception {
        Response response = target(PATH).request().get();
        responseHasStatus(response, 200);
    }

    @Test
    public void should_getting_all_cards_call_repository() throws Exception {
        target(PATH).request().get();
        verify(repository).getAll();
    }

    @Test
    public void should_getting_all_cards_equals_file() throws Exception {
        Response response = target(PATH).request().get();
        assertResponseEqualsFile(response, "container_with_all_cards.json");
    }

    @Test
    public void should_return_200_for_getting_single_card_with_path() throws Exception {
        Response response = target(PATH + "/12").request().get();
        responseHasStatus(response, 200);
    }

    @Test
    public void should_getting_single_card_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12").request().get();
        assertResponseEqualsFile(response, "container_with_single_card.json");
    }

    @Test
    public void should_return_200_for_getting_multiple_cards_with_path() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        responseHasStatus(response, 200);
    }

    @Test
    public void should_getting_multiple_cards_by_id_equals_file() throws Exception {
        Response response = target(PATH + "/12,14").request().get();
        assertResponseEqualsFile(response, "container_with_multiple_cards.json");
    }

    @Test
    public void should_return_200_when_add_a_card() throws Exception {
        Response response = target(PATH).request().post(Entity.json(newCard()));
        responseHasStatus(response, 200);
    }

    @Test
    public void should_getting_card_with_id_when_add_a_card() throws Exception {
        Response response = target(PATH).request().post(Entity.json(newCard()));

        assertResponseEqualsFile(response, "single_card.json");
        verify(addCard, times(1)).handle(any(AddCardCommand.class));
    }

    @Test
    public void should_getting_all_cards_returns_initial_cards_with_added_ones() throws Exception {

        target(PATH).request().post(Entity.json(newCard()));
        target(PATH).request().post(Entity.json(existingItem()));

        Response response = target(PATH).request().get();
        assertResponseEqualsFile(response, "container_with_all_cards_and_added_ones.json");
    }

    @Test
    public void should_add_multiple_cards_returns_initial_cards_with_added_ones() throws Exception {
        CardContainer cardContainer = new CardContainer(Arrays.asList(newCard(), existingItem()));
        target(PATH + "/list").request().post(Entity.json(cardContainer));

        Response response = target(PATH).request().get();
        assertResponseEqualsFile(response, "container_with_all_cards_and_added_ones.json");
    }

    //
    // Utils
    //

    private WebTarget target(String path) {
        return jersey.target(path);
    }

    private Card existingItem() {
        return Card.builder().id(5).title("Item existant").description("Cette carte était déjà existante").build();
    }

    private Card newCard() {
        return Card.builder().title("Nouvel item").description("Cet item est nouveau").build();
    }

    private void responseHasStatus(Response response, int expectedResponseStatus) {
        assertThat(response.getStatus()).as(response.readEntity(String.class)).isEqualTo(expectedResponseStatus);
    }

    private void assertResponseEqualsFile(Response response, String fileName) {
        assertThat(response.readEntity(String.class)).isEqualTo(getFileAsJson(fileName));
    }

    private String getFileAsJson(String filePath) {
        return FileTestUtils.getResourceFileAsJson(getClass(), filePath);
    }

}
