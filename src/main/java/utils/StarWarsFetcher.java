/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DTO.StarWarsDTO;
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
    
    public static String fetchPersonFromStarWarsApi(int id) throws IOException{
        String sw_person_url = "https://www.swapi.tech/api/people/" + id; 
        
        String person_data = HttpUtils.fetchData(sw_person_url);
        System.out.println(person_data);
        StarWarsDTO swDTO = gson.fromJson(person_data, StarWarsDTO.class);
        
        return gson.toJson(swDTO); 
    }
    
    public static void main(String[] args) throws IOException {
        String result = StarWarsFetcher.fetchPersonFromStarWarsApi(1); 
        System.out.println(result);
    }
    

}
