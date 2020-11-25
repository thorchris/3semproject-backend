package utils;

import DTO.GotDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author josef
 */
public class GotFetcher {

    private static String gotApi = "https://thronesapi.com/api/v2/Characters";

    public static String fetchDataFromGotApi() throws InterruptedException, IOException{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String got = HttpUtils.fetchData(gotApi);
        Type collectionType = new TypeToken<List<GotDTO>>() {
        }.getType();
        List<GotDTO> lcs = (List<GotDTO>) new Gson()
                .fromJson( got , collectionType);
       return gson.toJson(lcs);
    }

}
