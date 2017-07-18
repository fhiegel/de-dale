package com.dedale.cards;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dedale.cards.query.AddCardCommand;
import com.dedale.cards.query.FindCardsByIdsQuery;
import com.dedale.cards.query.GetAllCardsQuery;
import com.dedale.common.Dispatcher;

@Path(CardResource.PATH)
public class CardResource {

    static final String PATH = "cards";

    @Inject
    private Dispatcher dispatcher;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer all() {
        return dispatcher.dispatch(new GetAllCardsQuery());
    }

    @GET
    @Path("{cardIds}")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer getByIds(@PathParam("cardIds") String cardIds) {
        return dispatcher.dispatch(FindCardsByIdsQuery.parse(cardIds));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Card addCard(Card card) {
        return dispatcher.dispatch(AddCardCommand.from(card));
    }

    @POST
    @Path("list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer addContainer(CardContainer cardContainer) {
        for (Card card : cardContainer.getCards()) {
            addCard(card);
        }
        return all();
    }

}
