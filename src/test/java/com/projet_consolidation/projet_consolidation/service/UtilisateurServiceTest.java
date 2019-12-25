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

@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurService utilisateurService;

    Utilisateur user;

    @BeforeEach
    void beforeEach() {
        this.user = new Utilisateur("walid", "bizid", "walid.bizid@hotmail.com", LocalDate.of(1994,05,29));
        utilisateurRepository.save(user);
    }

    @Test
    public void getAllUsers(){

        Pageable pageRequest = PageRequest.of(0, 4);
        Page<Utilisateur> utilisateurPage = utilisateurService.getAllUsers(pageRequest);
        Utilisateur utilisateur = utilisateurPage.getContent().get(utilisateurPage.getContent().size()-1);
        assertEquals(user.getNom(), utilisateur.getNom());
    }

    @Test
    public void saveUser(){
        assertEquals(1.0, utilisateurRepository.count());
    }

    @Test
    public void shouldDeleteUser() {
        utilisateurService.deleteUser(user.getId());
        assertEquals(0, utilisateurRepository.count());
    }

    @Test
    void shouldReturnUserById() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        assertEquals(user.getNom(),utilisateurService.getUserById(utilisateurs.get(0).getId()).get().getNom());
    }

    @Test
    void shouldUpdateUser() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        Utilisateur updatedUser = utilisateurService.updateUser(new Utilisateur(utilisateurService.getUserById(utilisateurs.get(0).getId()).get().getId(),"walid", "bizid", "ali.bizid@gmail.com", LocalDate.of(1994,05,29)));
        Assert.assertEquals("ali.bizid@gmail.com",updatedUser.getEmail());
    }

    @AfterEach
    void afterEach(){
        utilisateurRepository.deleteAll();
    }
}
