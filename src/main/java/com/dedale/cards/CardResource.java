package com.dedale.cards;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(CardResource.PATH)
public class CardResource {
    
    static final String PATH = "cards";
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CardContainer all() {
        return new CardContainer();
    }
    
}
