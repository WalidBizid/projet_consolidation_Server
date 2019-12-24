package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UtilisateurService {
    void saveUser(Utilisateur utilisateur);
    Page<Utilisateur> getAllUsers(Pageable pageable);
    Utilisateur updateUser(Utilisateur utilisateur);
    void deleteUser(Long userId);
}
