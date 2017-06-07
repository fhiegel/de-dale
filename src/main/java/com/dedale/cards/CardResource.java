package com.dedale.cards;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(CardResource.PATH)
public class CardResource {
    
    static final String PATH = "cards";
    
    @Inject
    private FindCardsByIdsQueryHandler findById;
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer all() {
        return new CardContainer();
    }
    
    @GET
    @Path("{cardIds}")
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer byIds(@PathParam("cardIds") String cardIds) {
        return findById.handle(FindCardsByIdsQuery.parse(cardIds));
    }
    
}
