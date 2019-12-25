package com.projet_consolidation.projet_consolidation.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/users")
    public ResponseEntity<Page<Utilisateur>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(utilisateurService.getAllUsers(pageable), HttpStatus.OK);
    }

    @PostMapping("/user")
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ResponseEntity<Utilisateur> toCreateUser(@JsonProperty("nom") String nom,
                                                    @JsonProperty("prenom") String prenom,
                                                    @JsonProperty("eamil") String email,
                                                    @JsonProperty("date_de_naissance") @JsonFormat(pattern = "MM/dd/yyyy") LocalDate date_de_naissance
                                                    ){
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, date_de_naissance);
        return new ResponseEntity<>(utilisateurService.saveUser(utilisateur), HttpStatus.CREATED);
    }

    @RequestMapping(value = "user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> toDeleteUser(@PathVariable Long userId){
            utilisateurService.deleteUser(userId);
            return new ResponseEntity<>("user deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Optional<Utilisateur>> toGetUserById(@PathVariable long userId){
        return new ResponseEntity<>(utilisateurService.getUserById(userId), HttpStatus.OK);
    }

}
