package com.projet_consolidation.projet_consolidation.web;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet_consolidation.projet_consolidation.business.UtilisateurService;
import com.projet_consolidation.projet_consolidation.infrastructure.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;


    @Autowired
    private ObjectMapper objectMapper;

//    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//    private static final LocalDate simpleDateFormat = LocalDate.parse(,FORMATTER);
//
//
//    private UtilisateurDTO getUtilisateurDTOFromJson(final String jsonUtilisateurDTO) throws IOException {
//        return objectMapper.setDateFormat(FORMATTER).readValue(jsonUtilisateurDTO, UtilisateurDTO.class);
//    }
    /**
     * Get All users request
     *
     * @param pageable getting the result by page and not all in one response
     * @return Users by page
     */
    @GetMapping("/users")
    public ResponseEntity<Page<Utilisateur>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(utilisateurService.getAllUsers(pageable), HttpStatus.OK);
    }

    /**
     * Create a new User request
     *
     * @param nom family name of the user
     * @param prenom name of the user
     * @param email user's mail address
     * @param date_de_naissance user birth date
     * @return the user created
     */
    @PostMapping("/user")
    public ResponseEntity<Utilisateur> toCreateUser(@JsonProperty("nom") String nom,
                                                    @JsonProperty("prenom") String prenom,
                                                    @JsonProperty("eamil") String email,
                                                    @JsonProperty("date_de_naissance") @JsonFormat(pattern = "MM/dd/yyyy") LocalDate date_de_naissance
                                                    ){
        Utilisateur utilisateur = new Utilisateur(prenom, nom, email, date_de_naissance);
        return new ResponseEntity<>(utilisateurService.saveUser(utilisateur), HttpStatus.CREATED);
    }

    /**
     * Delete a specific user request
     *
     * @param userId the id of the user to delete
     * @return a string that mention that the user was deleted with a no content http status
     * @throws ResponseStatusException if user not exist in database
     * @throws HttpClientErrorException.BadRequest if entered id in the path not valid
     */
    @DeleteMapping("user/{userId}")
    public ResponseEntity<String> toDeleteUser(@PathVariable Long userId){
        if( utilisateurService.getUserById(userId).isPresent()){
            utilisateurService.deleteUser(userId);
            return new ResponseEntity<>("user deleted successfully", HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
    }

    /**
     * Get a specific user request
     *
     * @param userId the id of the user to get in the path url
     * @return the user with the given id and with ok http status
     * @throws ResponseStatusException if user not exist in database
     * @throws HttpClientErrorException.BadRequest if entered id in the path not valid
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<Utilisateur> toGetUserById(@PathVariable long userId){
        Optional<Utilisateur> result = utilisateurService.getUserById(userId);
        if( result.isPresent()){
            Utilisateur searchedUser = result.get();
            return new ResponseEntity<>(searchedUser, HttpStatus.OK);
        }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
    }

    /**
     * Update a specific user request
     *
     * @param userId the id of the user to update
     * @param nom the new family name of the user if changed or the existing one
     * @param prenom the new name of the user if changed or the existing one
     * @param email the new mail address of the user if changed or the existing one
     * @param date_de_naissance the new birth date of the user if changed or the existing one
     * @return the user entity but with the updated properties with the ok http status
     * @throws ResponseStatusException if user not exist in database
     */
    @PutMapping("user/{userId}")
    public ResponseEntity<Utilisateur> toUpdateUser(
            @PathVariable long userId,
            @JsonProperty("nom") String nom,
            @JsonProperty("prenom") String prenom,
            @JsonProperty("eamil") String email,
            @JsonProperty("date_de_naissance") @JsonFormat(pattern = "MM/dd/yyyy") LocalDate date_de_naissance
    ){
        if(utilisateurService.getUserById(userId).isPresent()) {
            Utilisateur UpdatedUser = new Utilisateur(userId, prenom, nom, email, LocalDate.of(date_de_naissance.getYear(), date_de_naissance.getMonth(), date_de_naissance.getDayOfMonth()));
            return new ResponseEntity<>(utilisateurService.updateUser(UpdatedUser), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
    }

}
