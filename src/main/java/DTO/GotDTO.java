package DTO;

/**
 *
 * @author josef
 */
public class GotDTO {

    private String fullName;
    private String title;
    private String family;

    public GotDTO(String fullName, String title, String family) {
        this.fullName = fullName;
        this.title = title;
        this.family = family;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

}
