/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.VoteDTO;
import entities.Vote;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

/**
 *
 * @author Frederik Dahl <cph-fd76@cphbusiness.dk>
 */
public class VoteFacadeTest {

    private static EntityManagerFactory emf;
    private static VoteFacade facade;
    private Vote vote;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = VoteFacade.getVoteFacade(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        vote = new Vote("Obi Wan Kenobi");
        try {
            em.getTransaction().begin();
            em.createQuery("Delete from Vote").executeUpdate();
            em.persist(vote);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetVoteFromDBwhereScoreEqualsOne() {
        String characterName = "Obi Wan Kenobi";
        
        VoteDTO vote2 = facade.getVoteFromDB(characterName);
        
        int expectedVoteScore = 1; 
        int resultVoteScore = vote2.getVoteScore(); 
        
        assertEquals(expectedVoteScore, resultVoteScore); 
        
    }
    
    @Test
    public void testGetVoteFromDBwhereScoreEqualsTwo() {
        String characterName = "Obi Wan Kenobi";
        facade.addVote(characterName);
        VoteDTO vote2 = facade.getVoteFromDB(characterName);
        
        int expectedVoteScore = 2; 
        int resultVoteScore = vote2.getVoteScore(); 
        
        assertEquals(expectedVoteScore, resultVoteScore); 
        
    }
    
    @Test
    public void testGetVoteFromDBwhereUserIsNotInDB() {
        String characterName = "Not in db";
        VoteDTO vote2 = facade.getVoteFromDB(characterName);
        
        int expectedVoteScore = 0; 
        int resultVoteScore = vote2.getVoteScore(); 
        
        assertEquals(expectedVoteScore, resultVoteScore); 
        
    }
    
    
    @Test
    public void testAddVoteToCharWithNoScore() {
        String newCharactername = "Anakin Skywalker"; 
        
        facade.addVote(newCharactername);
        VoteDTO vote2 = facade.getVoteFromDB(newCharactername);
        
        int expectedVoteScore = 1; 
        int resultVoteScore = vote2.getVoteScore(); 
        
        assertEquals(expectedVoteScore, resultVoteScore); 
        
        
    }

    @Test
    public void testAddVoteToCharWithScore() {
         String characterName = "Obi Wan Kenobi"; 
        
        facade.addVote(characterName);
        VoteDTO vote2 = facade.getVoteFromDB(characterName);
        
        int expectedVoteScore = 2; 
        int resultVoteScore = vote2.getVoteScore(); 
        
        assertEquals(expectedVoteScore, resultVoteScore); 
        
        
    }

    @Test
    public void testIsAlreadyInDatabaseExpectedFalse() {
        String characterName = "Harry Potter";
        Boolean shouldBeFalse = facade.isAlreadyInDatabase(characterName);

        assertFalse(shouldBeFalse);
    }

    @Test
    public void testIsAlreadyInDatabaseExpectedTrue() {
        String characterName = "Obi Wan Kenobi";
        Boolean shouldBeTrue = facade.isAlreadyInDatabase(characterName);

        assertTrue(shouldBeTrue);
    }

    

}
