/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.List;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class StarWarsListDTO {
    private List<StarWarsDTO> results;

    public List<StarWarsDTO> getStarWarsDTOList() {
        return results;
    }
    
    public StarWarsListDTO(List<StarWarsDTO> results) {
        this.results = results;
    }

    public void setStarWarsDTOList(List<StarWarsDTO> results) {
        this.results = results;
    }
    
    
    

}
