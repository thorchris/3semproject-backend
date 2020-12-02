package fetchers;

import DTO.CombinedListDTO;
import DTO.GotDTO;
import DTO.GotListDTO;
import DTO.HarryPotterDTO;
import DTO.HarryPotterListDTO;
import DTO.StarWarsDTO;
import DTO.StarWarsListDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import utils.HttpUtils;

public class SearchFetcher {

    private static String swApi = "https://www.swapi.tech/api/people?page=1&limit=82";
    private static String hpApi = "http://hp-api.herokuapp.com/api/characters";
    private static String gotApi = "https://thronesapi.com/api/v2/Characters";

    public static String searchFetcherApi(ExecutorService threadPool, final Gson gson) throws Exception {

        Callable<List<HarryPotterDTO>> hpTask = new Callable<List<HarryPotterDTO>>() {
            @Override
            public List<HarryPotterDTO> call() throws Exception {
                String charString = HttpUtils.fetchData(hpApi);
                Type collectionType = new TypeToken<List<HarryPotterDTO>>() {
                }.getType();
                List<HarryPotterDTO> lcs = (List<HarryPotterDTO>) new Gson()
                        .fromJson(charString, collectionType);

                return lcs;
            }

        };

        Callable<List<GotDTO>> gotTask = new Callable<List<GotDTO>>() {
            @Override
            public List<GotDTO> call() throws Exception {
                String charString = HttpUtils.fetchData(gotApi);
                Type collectionType = new TypeToken<List<GotDTO>>() {
                }.getType();
                List<GotDTO> lcs = (List<GotDTO>) new Gson()
                        .fromJson(charString, collectionType);

                return lcs;
            }

        };

        Callable<List<StarWarsDTO>> swTask = new Callable<List<StarWarsDTO>>() {
            @Override
            public List<StarWarsDTO> call() throws Exception {
                String charString = HttpUtils.fetchData(swApi);
                StarWarsListDTO lcs = (StarWarsListDTO) gson.fromJson(charString, StarWarsListDTO.class);
                return lcs.getStarWarsDTOList();
            }

        };

        Future<List<HarryPotterDTO>> futureChar1 = threadPool.submit(hpTask);
        Future<List<GotDTO>> futureChar2 = threadPool.submit(gotTask);
        Future<List<StarWarsDTO>> futureChar3 = threadPool.submit(swTask);

        List<HarryPotterDTO> charDTOList1 = futureChar1.get(2, TimeUnit.SECONDS);
        List<GotDTO> charDTOList2 = futureChar2.get(2, TimeUnit.SECONDS);
        List<StarWarsDTO> charDTOList3 = futureChar3.get(2, TimeUnit.SECONDS);

        HarryPotterListDTO hpList = new HarryPotterListDTO(charDTOList1);
        GotListDTO gotList = new GotListDTO(charDTOList2);
        StarWarsListDTO swList = new StarWarsListDTO(charDTOList3);

        CombinedListDTO combinedList = new CombinedListDTO(gotList, hpList, swList);

        String standartJSON = gson.toJson(combinedList);

        return standartJSON;
    }

}
