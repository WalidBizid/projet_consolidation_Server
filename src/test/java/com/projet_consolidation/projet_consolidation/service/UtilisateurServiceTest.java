package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    UtilisateurService utilisateurService;

    @Test
    public void getAllUsers(){
        Utilisateur user1 = new Utilisateur("walid", "bizid", "walid.bizid@hotmail.com", LocalDate.of(1994,05,29));
        utilisateurRepository.save(user1);

        Pageable pageRequest = PageRequest.of(0, 4);
        Page<Utilisateur> utilisateurPage = utilisateurService.getAllUsers(pageRequest);
        Utilisateur utilisateur = utilisateurPage.getContent().get(utilisateurPage.getContent().size()-1);
        assertEquals(user1.getNom(), utilisateur.getNom());
    }
}
