/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DTO.StarWarsListDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

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


}
