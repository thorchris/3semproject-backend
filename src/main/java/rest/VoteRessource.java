/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTO.VoteDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import facades.VoteFacade;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Path("vote")
public class VoteRessource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static VoteFacade facade = VoteFacade.getVoteFacade(EMF);
    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "Vote page is up and running";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{characterName}")
    public String getVote(@PathParam("characterName") String characterName) throws IOException, InterruptedException, ParseException {
        VoteDTO vote = facade.getVoteFromDB(characterName);

        return gson.toJson(vote);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("upvote/{characterName}")
    public String addVote(@PathParam("characterName") String characterName) throws IOException, InterruptedException, ParseException {
        facade.addVote(characterName);
        return gson.toJson("Succes! The " + characterName + " has been upvoted");
    }
}
