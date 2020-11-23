package utils;

import DTO.HarryPotterDTOs;
import DTO.HarryPotterDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import utils.HttpUtils;


public class HarryPotterFethcer {
    
      public static String bbFetch(ExecutorService threadPool, final Gson gson, String FACT_SERVER) throws Exception{
        
        Callable<List<HarryPotterDTO>> charTask = new Callable<List<HarryPotterDTO>>(){
            @Override
            public List<HarryPotterDTO> call() throws Exception {
                String charString = HttpUtils.fetchData(FACT_SERVER);
                
                Type collectionType = new TypeToken<List<HarryPotterDTO>>(){}.getType();
                List<HarryPotterDTO> lcs = (List<HarryPotterDTO>) new Gson()
                    .fromJson( charString , collectionType);
                
                return lcs;
            }
            
        };
        Future<List<HarryPotterDTO>> futureChar = threadPool.submit(charTask);
        
        List<HarryPotterDTO> charDTOList = futureChar.get(2, TimeUnit.SECONDS);
         
        HarryPotterDTOs sDTO = new HarryPotterDTOs(charDTOList);
        
        String standartJSON = gson.toJson(sDTO);
          
        return standartJSON;
    }    
    
}
