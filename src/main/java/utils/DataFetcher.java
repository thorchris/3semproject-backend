package utils;

import DTO.StarWarsDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DataFetcher {

    private static String swApi = "https://www.swapi.tech/api/people/";



    

    public static String fetchDataFromSwApi() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String sw = HttpUtils.fetchData(swApi);
        StarWarsDTO starwarsDTO = gson.fromJson(sw, StarWarsDTO.class);
        return gson.toJson(starwarsDTO);
    }
    

//    
//    public static String fetchDataFromApi(ExecutorService executorService) throws InterruptedException, ExecutionException, TimeoutException, IOException {
//        long start = System.nanoTime();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        Callable<GotDTO> gotTask = new Callable<GotDTO>() {
//            @Override
//            public GotDTO call() throws Exception {
//                String got = HttpUtils.fetchData(gotApi);
//                GotDTO gotDTO = gson.fromJson(got, GotDTO.class);
//                return gotDTO;
//            }
//        };
//
//        Callable<StarWarsDTO> swTask = new Callable<StarWarsDTO>() {
//            @Override
//            public StarWarsDTO call() throws Exception {
//                String sw = HttpUtils.fetchData(swApi);
//                StarWarsDTO starwarsDTO = gson.fromJson(sw, StarWarsDTO.class);
//                return starwarsDTO;
//            }
//        };
//
//        Callable<BreakingBadDTO> bbTask = new Callable<BreakingBadDTO>() {
//            @Override
//            public BreakingBadDTO call() throws Exception {
//                String bb = HttpUtils.fetchData(bbApi);
//                BreakingBadDTO breakingbadDTO = gson.fromJson(bb, BreakingBadDTO.class);
//                return breakingbadDTO;
//            }
//        };
//
//        Future<GotDTO> futureGot = executorService.submit(gotTask);
//        Future<StarWarsDTO> futureSw = executorService.submit(swTask);
//        Future<BreakingBadDTO> futureBB = executorService.submit(bbTask);
//
//        GotDTO got = futureGot.get(2, TimeUnit.SECONDS);
//        StarWarsDTO sw = futureSw.get(2, TimeUnit.SECONDS);
//        BreakingBadDTO bb = futureBB.get(2, TimeUnit.SECONDS);
//
//        return "Data fetched";
//
//    }

}
