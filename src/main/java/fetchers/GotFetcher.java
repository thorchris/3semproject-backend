package fetchers;

import DTO.GotDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import utils.HttpUtils;

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
