package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.repository.UtilisateurRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A test class for the user service
 *
 * @author walid BIZID
 * @version 1
 */
@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurService utilisateurService;

    Utilisateur user;

    /**
     * Should instantiate a user and save it into the database before each test
     */
    @BeforeEach
    void shouldCreateNewUserAndSaveItInTheDatabaseBeforeEachTest() {
        this.user = new Utilisateur("walid", "bizid", "walid.bizid@hotmail.com", LocalDate.of(1994,05,29));
        utilisateurRepository.save(user);
    }

    /**
     * Should return all the users saved in the database by page
     */
    @Test
    public void shouldGetAllUsersByPageFromDatabase(){

        Pageable pageRequest = PageRequest.of(0, 4);
        Page<Utilisateur> utilisateurPage = utilisateurService.getAllUsers(pageRequest);
        Utilisateur utilisateur = utilisateurPage.getContent().get(utilisateurPage.getContent().size()-1);
        assertEquals(user.getNom(), utilisateur.getNom());
    }

    /**
     * Should save a new user in the database
     */
    @Test
    public void shouldSaveUserInTheDatabase(){
        assertEquals(1.0, utilisateurRepository.count());
    }

    /**
     * Should delete a specific user (by his id) from the database
     */
    @Test
    public void shouldDeleteSpecificUserfromDatabase() {
        utilisateurService.deleteUser(user.getId());
        assertEquals(0, utilisateurRepository.count());
    }

    /**
     * Should return a specific user (by his id) from the database
     */
    @Test
    void shouldReturnUserByIdFromDatabase() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        assertEquals(user.getNom(),utilisateurService.getUserById(utilisateurs.get(0).getId()).get().getNom());
    }

    /**
     * Should update a specific user in the database
     */
    @Test
    void shouldUpdateUserInDatabase() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        Utilisateur updatedUser = utilisateurService.updateUser(new Utilisateur(utilisateurService.getUserById(utilisateurs.get(0).getId()).get().getId(),"walid", "bizid", "ali.bizid@gmail.com", LocalDate.of(1994,05,29)));
        Assert.assertEquals("ali.bizid@gmail.com",updatedUser.getEmail());
    }

    /**
     * Should be executed after each test to delete all the users in the database
     */
    @AfterEach
    void shouldDeleteAllUsersInDatabaseAfterEachTest(){
        utilisateurRepository.deleteAll();
    }
}
