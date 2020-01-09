package com.projet_consolidation.projet_consolidation.business;

import com.projet_consolidation.projet_consolidation.infrastructure.Utilisateur;
import com.projet_consolidation.projet_consolidation.infrastructure.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    /**
     * Save a new user
     *
     * @param utilisateur the user to save in the database
     * @return the user saved in the database
     */
    public Utilisateur saveUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Get all the users from the database
     *
     * @param pageable indicate that the returned users should be by page
     * @return All users by page
     */
    public Page<Utilisateur> getAllUsers(Pageable pageable) {
        return utilisateurRepository.findAll(pageable);
    }

    public List<Utilisateur> getUsers(){ return  utilisateurRepository.findAll(); }

    /**
     * Update a specific user in the database
     *
     * @param utilisateur the user with the new properties
     * @return the updated user from the database
     */
    public Utilisateur updateUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Delete a specific user from the database
     *
     * @param userId the user's id to delete
     */
    public void deleteUser(Long userId) {
        utilisateurRepository.deleteById(userId);
    }

    /**
     * Get a specific user from the database
     *
     * @param id the user's id to return
     * @return the user with the specific id
     */
    public Optional<Utilisateur> getUserById(long id) {
        return utilisateurRepository.findById(id);
    }
}
