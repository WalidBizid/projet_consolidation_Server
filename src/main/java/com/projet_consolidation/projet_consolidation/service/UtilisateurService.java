package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UtilisateurService {

    Utilisateur saveUser(Utilisateur utilisateur);

    Page<Utilisateur> getAllUsers(Pageable pageable);

    Utilisateur updateUser(Utilisateur utilisateur);

    void deleteUser(Long userId);

    Optional<Utilisateur> getUserById(long id);
}
