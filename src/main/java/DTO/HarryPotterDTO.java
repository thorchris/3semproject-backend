package DTO;

public class HarryPotterDTO {
    
    private String name;
    private String house;
    private String dateOfBirth;
    private String ancestry;
 

    public HarryPotterDTO(String name, String occupation, String birthday, String ancestry) {
        this.name = name;
        this.house = occupation;
        this.dateOfBirth = birthday;
        this.ancestry = ancestry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }
    
    


    
    
    

}
