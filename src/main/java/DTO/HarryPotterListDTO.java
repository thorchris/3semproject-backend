package DTO;

import java.util.ArrayList;
import java.util.List;

public class HarryPotterListDTO {
    
    private List<HarryPotterDTO> hpDTOList = new ArrayList();

    public HarryPotterListDTO(List<HarryPotterDTO> bList) {
        this.hpDTOList = bList;
    }

    public List<HarryPotterDTO> getHpDTOList() {
        return hpDTOList;
    }
            
}
