/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fetchers;

import DTO.StarWarsDTO;
import DTO.StarWarsListDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import java.io.IOException;
import utils.HttpUtils;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class StarWarsFetcher {

    private static String swApi = "https://www.swapi.tech/api/people?page=1&limit=82";
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String fetchDataFromStarWarsApi() throws InterruptedException, IOException {
        String sw = HttpUtils.fetchData(swApi);

        StarWarsListDTO lcs = (StarWarsListDTO) gson.fromJson(sw, StarWarsListDTO.class);
        return gson.toJson(lcs);
    }


    public static String fetchPersonFromStarWarsApi(int id) throws IOException, ParseException {
        String sw_person_url = "https://www.swapi.tech/api/people/" + id;

        String person_data = HttpUtils.fetchData(sw_person_url);
        
        JsonElement jelement = new JsonParser().parse(person_data);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("result");
        
        JsonObject starWarsProperties = jobject.getAsJsonObject("properties");
        StarWarsDTO swDTO = gson.fromJson(starWarsProperties, StarWarsDTO.class);
        swDTO.setUid(id);
        
        String result = gson.toJson(swDTO);
        return result;
    }
    
    
    public static void main(String[] args) throws IOException, ParseException {
        String result = StarWarsFetcher.fetchPersonFromStarWarsApi(1);
        System.out.println(result);
    }

}
