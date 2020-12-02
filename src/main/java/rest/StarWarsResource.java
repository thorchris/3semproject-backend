/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;
import fetchers.StarWarsFetcher;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("starwars")
public class StarWarsResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allStarWars() throws IOException, InterruptedException {
        return StarWarsFetcher.fetchDataFromStarWarsApi();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("character/{id}")
    public String getPerson(@PathParam("id") int id) throws IOException, InterruptedException, ParseException {
        return StarWarsFetcher.fetchPersonFromStarWarsApi(id);
    }
}
