package com.dedale.cards;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(CardResource.PATH)
public class CardResource {

    static final String PATH = "cards";

    @Inject
    private GetAllCardsQueryHandler getCards;
    @Inject
    private FindCardsByIdsQueryHandler findById;
    @Inject
    private AddCardCommandHandler addCard;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer all() {
        return new CardContainer(getCards.handle(new GetAllCardsQuery()));
    }

    @GET
    @Path("{cardIds}")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer getByIds(@PathParam("cardIds") String cardIds) {
        return new CardContainer(findById.handle(FindCardsByIdsQuery.parse(cardIds)));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Card addCard(Card card) {
        return addCard.handle(AddCardCommand.from(card));
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
