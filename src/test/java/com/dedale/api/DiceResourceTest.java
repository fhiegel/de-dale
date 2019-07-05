package com.dedale.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class DiceResourceTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void should_return_200_for_well_formed_dice_query() {
        // Given
        String wellFormedQuery = "1+1";

        // When
        HttpResponse<String> dices = postDiceQuery(wellFormedQuery);

        // Then
        assertEquals(dices.getStatus(), HttpStatus.OK);
    }

    @Test
    void should_return_appropriate_result_for_well_formed_dice_query() {
        // Given
        String wellFormedQuery = "1+1";

        // When
        String responseMsg = postDiceQueryAsString(wellFormedQuery);

        // Then
        assertThat(responseMsg).contains("2");
    }

    @Test
    void should_roll_a_dice_twice() {
        // Given
        String wellFormedQuery = "1+2 3+4";

        // When
        String responseMsg = postDiceQueryAsString(wellFormedQuery);

        // Then
        assertThat(responseMsg).contains("3 7");
    }

    @Test
    void should_roll_a_dice_twice_despite_spaces() {
        // Given
        String wellFormedQuery = "1+ 2 3+ 4";

        // When
        String responseMsg = postDiceQueryAsString(wellFormedQuery);

        // Then
        assertThat(responseMsg).contains("3 7");
    }

    @Test
    @Disabled
    void should_return_400_for_malformed_dice_query() {
        // Given
        String malFormedQuery = "malFormedQuery";

        // When
        HttpResponse<String> responseMsg = postDiceQuery(malFormedQuery);

        // Then
        assertEquals(responseMsg.getStatus(), HttpStatus.BAD_REQUEST);
    }


    private HttpResponse<String> postDiceQuery(String wellFormedQuery) {
        return client.toBlocking()
                .exchange(HttpRequest.POST("dices", wellFormedQuery)
                        .accept(MediaType.TEXT_PLAIN_TYPE)
                        .contentType(MediaType.TEXT_PLAIN_TYPE));
    }

    private String postDiceQueryAsString(String wellFormedQuery) {
        return client.toBlocking()
                .retrieve(HttpRequest.POST("dices", wellFormedQuery)
                        .accept(MediaType.TEXT_PLAIN_TYPE)
                        .contentType(MediaType.TEXT_PLAIN_TYPE));
    }

}

