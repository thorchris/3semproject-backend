package DTO;

import java.util.List;

/**
 *
 * @author josef
 */
public class GotListDTO {

    private List<GotDTO> results;

    public List<GotDTO> getGotListDTO() {
        return results;
    }
    
    public GotListDTO(List<GotDTO> results) {
        this.results = results;
    }

    public void setGotListDTO(List<GotDTO> results) {
        this.results = results;
    }

}
