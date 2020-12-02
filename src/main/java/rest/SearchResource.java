package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import fetchers.SearchFetcher;
import javax.ws.rs.core.UriInfo;

@Path("search")
public class SearchResource {

    @Context
    private UriInfo context;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService es = Executors.newCachedThreadPool();
    private static String response;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String allSearch() throws IOException, Exception {
        response = SearchFetcher.searchFetcherApi(es, GSON);
        return response;
    }
}
