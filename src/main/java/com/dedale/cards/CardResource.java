package com.dedale.cards;

import com.dedale.cards.query.AddCard;
import com.dedale.cards.query.FindCardsByIds;
import com.dedale.cards.query.GetAllCards;
import com.dedale.core.query.Dispatcher;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;

@Controller(CardResource.PATH)
public class CardResource {

    static final String PATH = "/cards";

    private final Dispatcher dispatcher;

    @Inject
    public CardResource(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer all() {
        return dispatcher.dispatch(new GetAllCards());
    }

    @Get("{cardIds}")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer getByIds(@PathVariable("cardIds") String cardIds) {
        return dispatcher.dispatch(FindCardsByIds.parse(cardIds));
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Card addCard(Card card) {
        return dispatcher.dispatch(AddCard.from(card));
    }

    @Post("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer addContainer(CardContainer cardContainer) {
        for (Card card : cardContainer.getCards()) {
            addCard(card);
        }
        return all();
    }

}
