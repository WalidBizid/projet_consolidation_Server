package com.projet_consolidation.projet_consolidation.controller;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/users")
    public Page<Utilisateur> getAllUsers(Pageable pageable){
        return utilisateurRepository.findAll(pageable);
    }
}
