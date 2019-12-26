package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /**
     * Save a new user
     *
     * @param utilisateur the user to save in the database
     * @return the user saved in the database
     */
    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Get all the users from the database
     *
     * @param pageable indicate that the returned users should be by page
     * @return All users by page
     */
    @Override
    public Page<Utilisateur> getAllUsers(Pageable pageable) {
        return utilisateurRepository.findAll(pageable);
    }

    /**
     * Update a specific user in the database
     *
     * @param utilisateur the user with the new properties
     * @return the updated user from the database
     */
    @Override
    public Utilisateur updateUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Delete a specific user from the database
     *
     * @param userId the user's id to delete
     */
    @Override
    public void deleteUser(Long userId) {
        utilisateurRepository.deleteById(userId);
    }

    /**
     * Get a specific user from the database
     *
     * @param id the user's id to return
     * @return the user with the specific id
     */
    @Override
    public Optional<Utilisateur> getUserById(long id) {
        return utilisateurRepository.findById(id);
    }
}
