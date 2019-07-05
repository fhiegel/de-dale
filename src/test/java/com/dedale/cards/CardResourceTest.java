package com.dedale.cards;

import com.dedale.cards.query.AddCard;
import com.dedale.cards.query.AddCardHandler;
import com.dedale.cards.query.FindCardsByIdsHandler;
import com.dedale.cards.query.GetAllCardsHandler;
import com.dedale.core.query.Dispatcher;
import com.dedale.core.query.QueryHandler;
import com.dedale.utils.FileTestUtils;
import com.dedale.utils.JsonUtils;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;

import static com.dedale.cards.CardResource.PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@MicronautTest
class CardResourceTest {

    @MockBean(Cards.class)
    Cards getCards() {
        return spy(new CardRepositoryInMemory());
    }

    @MockBean(AddCardHandler.class)
    AddCardHandler addCards(Cards repository) {
        return spy(new AddCardHandler(repository));
    }

    @Inject
    private Cards repository;

    @Inject
    private AddCardHandler addCard;

    @Inject
    @Client(PATH)
    HttpClient client;

    @BeforeEach
    void beforeName() {
        resetRepository(repository);
//        Mockito.reset(repository);
    }

    private static void resetRepository(Cards repository) {
        ((CardRepositoryInMemory) repository).clear();
        repository.add(CardBuilder.builder().id(12).title("Titre 12").build());
        repository.add(CardBuilder.builder().id(14).description("Description 14").build());
    }

    @Test
    void should_return_200_for_getting_all_cards() {
        CardContainer retrieve = client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        assertThat(retrieve).isNotNull();
    }

    @Test
    void should_getting_all_cards_call_repository() {
        client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        verify(repository).getAll();
    }

    @Test
    void should_getting_all_cards_equals_file() {
        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_all_cards.json");
    }

    @Test
    void should_return_200_for_getting_single_card_with_path() {
        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET("12"), CardContainer.class);
//        responseHasStatus(response, 200);
        assertThat(container).isNotNull();
    }

    @Test
    void should_getting_single_card_by_id_equals_file() {
        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET("12"), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_single_card.json");
    }

    @Test
    void should_return_200_for_getting_multiple_cards_with_path() {
        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET("12,14"), CardContainer.class);
        assertThat(container).isNotNull();
    }

    @Test
    void should_getting_multiple_cards_by_id_equals_file() {
        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET("12,14"), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_multiple_cards.json");
    }

    @Test
    void should_return_200_when_add_a_card() {
        Card card = client.toBlocking()
                .retrieve(HttpRequest.POST("", newCard()), Card.class);
        assertThat(card).isNotNull();
    }

    @Test
    @Disabled("Micronaut bug not resolved: https://github.com/micronaut-projects/micronaut-test/issues/41 ")
    void should_getting_card_with_id_when_add_a_card() {
        Card card = client.toBlocking()
                .retrieve(HttpRequest.POST("", newCard()), Card.class);
        assertThat(card).isNotNull();

        assertResponseEqualsFile(card, "single_card.json");
        verify(addCard, times(1)).handle(any(AddCard.class));
    }

    @Test
    @Disabled("Micronaut bug not resolved: https://github.com/micronaut-projects/micronaut-test/issues/41 ")
    void should_getting_all_cards_returns_initial_cards_with_added_ones() {
        client.toBlocking()
                .retrieve(HttpRequest.POST("", newCard()), Card.class);
        client.toBlocking()
                .retrieve(HttpRequest.POST("", existingItem()), Card.class);

        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_all_cards_and_added_ones.json");
    }

    @Test
    @Disabled("Micronaut bug not resolved: https://github.com/micronaut-projects/micronaut-test/issues/41 ")
    void should_add_multiple_cards_returns_initial_cards_with_added_ones() {
        CardContainer cardContainer = new CardContainer(Arrays.asList(newCard(), existingItem()));
        client.toBlocking()
                .retrieve(HttpRequest.POST("list", cardContainer), CardContainer.class);

        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_all_cards_and_added_ones.json");
    }

    @Test
    @Disabled("Micronaut bug not resolved: https://github.com/micronaut-projects/micronaut-test/issues/41 ")
    void smoke() {
        client.toBlocking()
                .retrieve(HttpRequest.POST("list", getFileAsJson("cards_to_add.json")), CardContainer.class);

        CardContainer container = client.toBlocking()
                .retrieve(HttpRequest.GET(""), CardContainer.class);
        assertResponseEqualsFile(container, "container_with_all_cards_and_added_ones.json");
    }

    //
    // Utils
    //

    private Card existingItem() {
        return CardBuilder.builder().id(5).title("Item existant").description("Cette carte était déjà existante").build();
    }

    private Card newCard() {
        return CardBuilder.builder().title("Nouvel item").description("Cet item est nouveau").build();
    }

    private void assertResponseEqualsFile(Object container, String fileName) {
        assertThat(JsonUtils.asJson(container)).isEqualTo(getFileAsJson(fileName));
    }

    private String getFileAsJson(String filePath) {
        return FileTestUtils.getResourceFileAsJson(getClass(), filePath);
    }

}
