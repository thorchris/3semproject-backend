package DTO;

import java.util.ArrayList;
import java.util.List;

public class HarryPotterDTOs {
    
    private List<HarryPotterDTO> HpDTOList = new ArrayList();

    public HarryPotterDTOs(List<HarryPotterDTO> bList) {
        this.HpDTOList = bList;
    }

    public List<HarryPotterDTO> getHpDTOList() {
        return HpDTOList;
    }
            
}
