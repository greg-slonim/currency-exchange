package com.gslonim.exchange.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public interface ForeignExchangeService {
    /** A simple currency exchange endpoint */
    @POST
    @Path("exchange")
    ExchangeResult getExchangeAndRecommendations(ExchangeRequest request);
}
