/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.VoteDTO;
import entities.Vote;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import utils.EMF_Creator;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class VoteFacade {

    private static EntityManagerFactory emf;
    private static VoteFacade instance;

    public static VoteFacade getVoteFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new VoteFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void addVote(String characterName) {
        EntityManager em = emf.createEntityManager();
        Vote vote;

        try {
            em.getTransaction().begin();
            if (isAlreadyInDatabase(characterName)) {

                vote = em.find(Vote.class, characterName);
                int voteScore = vote.getVoteScore();
                vote.setVoteScore(voteScore + 1);
                em.persist(vote);

            } else {
                vote = new Vote(characterName);
                em.persist(vote);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public Boolean isAlreadyInDatabase(String characterName) {
        VoteDTO vote = getVoteFromDB(characterName);

        if (vote.getCharacterName().equals("No result")) {
            return false;
        } else {
            return true;
        }

    }

    public VoteDTO getVoteFromDB(String characterName) {
        EntityManager em = emf.createEntityManager();
        Vote vote;

        try {
            em.getTransaction().begin();
            vote = em.find(Vote.class, characterName);
            if (vote != null) {

                em.getTransaction().commit();
            } else {
                vote = new Vote("No result");
                vote.setVoteScore(0);
            }
        } catch (NoResultException e) {
            return null;

        } finally {
            em.close();
        }

        return new VoteDTO(vote);
    }

    //TODO DELETE ME
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        VoteFacade facade = VoteFacade.getVoteFacade(emf);
        EntityManager em = emf.createEntityManager();

        String characterName = "harry potter";

        Boolean shouldBeFalse = facade.isAlreadyInDatabase(characterName);
        System.out.println("First time searching for hp in db: "
                + shouldBeFalse);

        facade.addVote(characterName);
        VoteDTO vote = facade.getVoteFromDB(characterName);
        System.out.println("Vote from DB: " + vote.getCharacterName()); 
        System.out.println("First score: " + vote.getVoteScore());
        
        Boolean shouldBeTrue = facade.isAlreadyInDatabase(characterName);
        System.out.println("Second time searching for hp in db: "
                + shouldBeTrue);

        facade.addVote(characterName);

        vote = facade.getVoteFromDB(characterName);
        System.out.println("Second score: " + vote.getVoteScore());
    }

}
