/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import fetchers.StarWarsFetcher;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import rest.ApplicationConfig;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
@Disabled
public class StarWarsFetcherTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/jpareststarter/api";
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }
   
       @BeforeAll
    public static void setUpClass() {
        httpServer = startServer();
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        httpServer.shutdownNow();
    }
    
   
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/starwars").then().statusCode(200);
    }


    
    @Test
    public void testFetchDataFromStarWarsApi() throws Exception {
        System.out.println("fetchDataFromStarWarsApi");
        String expResult = "";
        String result = StarWarsFetcher.fetchDataFromStarWarsApi();
        assertNotEquals(expResult, result);
     
    }
    
}
