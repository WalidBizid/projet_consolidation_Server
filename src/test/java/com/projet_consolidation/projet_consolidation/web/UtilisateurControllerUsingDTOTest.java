package com.projet_consolidation.projet_consolidation.web;

import com.projet_consolidation.projet_consolidation.infrastructure.Utilisateur;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilisateurControllerUsingDTOTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertUtilisateurEntityToUtilisateurDto_thenCorrect() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("John");
        utilisateur.setPrenom("Traore");
        utilisateur.setEmail("john.traore@gmail.com");
        utilisateur.setDate_de_naissance(LocalDate.of(1950,12,31));

        UtilisateurDTO utilisateurDTO = modelMapper.map(utilisateur, UtilisateurDTO.class);
        assertEquals(utilisateur.getId(), utilisateurDTO.getId());
        assertEquals(utilisateur.getNom(), utilisateurDTO.getNom());
        assertEquals(utilisateur.getPrenom(), utilisateurDTO.getPrenom());
        assertEquals(utilisateur.getDate_de_naissance(), utilisateurDTO.getDate_de_naissance());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(1L);
        utilisateurDTO.setNom("John");
        utilisateurDTO.setPrenom("Traore");
        utilisateurDTO.setEmail("john.traore@gmail.com");
        utilisateurDTO.setDate_de_naissance(LocalDate.of(1950,12,31));

        Utilisateur utilisateur = modelMapper.map(utilisateurDTO, Utilisateur.class);
        assertEquals(utilisateurDTO.getId(), utilisateur.getId());
        assertEquals(utilisateurDTO.getNom(), utilisateur.getNom());
        assertEquals(utilisateurDTO.getPrenom(), utilisateur.getPrenom());
        assertEquals(utilisateurDTO.getEmail(), utilisateur.getEmail());
        assertEquals(utilisateurDTO.getDate_de_naissance(), utilisateur.getDate_de_naissance());
    }
}
