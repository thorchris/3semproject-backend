/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.Vote;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class VoteRessourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();

        httpServer.shutdownNow();
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        Vote vote = new Vote("Obi Wan Kenobi");
        try {
            em.getTransaction().begin();
            em.createQuery("Delete from Vote").executeUpdate();
            em.persist(vote);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void serverIsRunning() {
        given().when().get("/vote").then().statusCode(200);
    }

    @Test
    public void testGetVote() {
        given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .get("/vote/obi wan kenobi").then()
                .statusCode(200)
                .body(equalTo("{\n"
                        + "  \"voteScore\": 1,\n"
                        + "  \"characterName\": \"Obi Wan Kenobi\"\n"
                        + "}"));
    }

    @Test
    public void testAddVote() throws Exception {
         given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .when()
                .post("/vote/upvote/obi wan kenobi").then()
                .statusCode(200)
                .body(equalTo("\"Succes! The obi wan kenobi has been upvoted\""));
    }

}
