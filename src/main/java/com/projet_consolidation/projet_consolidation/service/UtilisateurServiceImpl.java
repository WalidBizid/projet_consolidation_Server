package com.projet_consolidation.projet_consolidation.service;

import com.projet_consolidation.projet_consolidation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projet_consolidation.projet_consolidation.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public void saveUser(Utilisateur utilisateur) {

    }

    @Override
    public Page<Utilisateur> getAllUsers(Pageable pageable) {
        return utilisateurRepository.findAll(pageable);
    }

    @Override
    public Utilisateur updateUser(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
