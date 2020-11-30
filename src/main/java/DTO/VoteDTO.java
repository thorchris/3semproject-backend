/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import entities.Vote;

/**
 * 
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class VoteDTO {
    private int voteScore; 
    private String characterName; 

    public VoteDTO(Vote vote) {
        this.voteScore = vote.getVoteScore(); 
        this.characterName = vote.getCharacterName();
    }

    public int getVoteScore() {
        return voteScore;
    }

    public String getCharacterName() {
        return characterName;
    }

}
