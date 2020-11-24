package DTO;

import java.util.List;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class StarWarsDTO {
    
    private String name; 
    private int uid; 
    private String url; 
    private String height; 
    private String mass; 
    private String eye_color; 

 
   
    public StarWarsDTO(String name, int uid, String url) {
        this.name = name;
        this.uid = uid; 
        this.url = url; 

    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    
    
    
    
    
}
