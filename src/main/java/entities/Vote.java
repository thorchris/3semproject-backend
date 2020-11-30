package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQuery(name = "Vote.deleteAllRows", query = "DELETE from Vote")
@Table(name = "vote")
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int voteScore; 
    @Id
    private String characterName; 

    public Vote(String characterName) {
        this.voteScore = 1; 
        this.characterName = characterName;
    }
    
    public Vote() {
    }

    public int getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(int voteScore) {
        this.voteScore = voteScore;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    
    
    
    
    

   
}
