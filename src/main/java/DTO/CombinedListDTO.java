package DTO;

import java.util.List;

/**
 *
 * @author josef
 */
public class CombinedListDTO {

    private GotListDTO gotList;
    private HarryPotterListDTO hpList;
    private StarWarsListDTO swList;

    public CombinedListDTO(GotListDTO gotList, HarryPotterListDTO hpList, StarWarsListDTO swList) {
        this.gotList = gotList;
        this.hpList = hpList;
        this.swList = swList;
    }

    public GotListDTO getGotList() {
        return gotList;
    }

    public void setGotList(GotListDTO gotList) {
        this.gotList = gotList;
    }

    public HarryPotterListDTO getHpList() {
        return hpList;
    }

    public void setHpList(HarryPotterListDTO hpList) {
        this.hpList = hpList;
    }

    public StarWarsListDTO getSwList() {
        return swList;
    }

    public void setSwList(StarWarsListDTO swList) {
        this.swList = swList;
    }

}
