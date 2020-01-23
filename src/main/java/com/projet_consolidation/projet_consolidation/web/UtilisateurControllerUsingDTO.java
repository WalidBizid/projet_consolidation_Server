package com.projet_consolidation.projet_consolidation.web;

import com.projet_consolidation.projet_consolidation.business.UtilisateurService;
import com.projet_consolidation.projet_consolidation.infrastructure.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
public class UtilisateurControllerUsingDTO {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/users")
    public List<UtilisateurDTO> getUsers(){
        List<Utilisateur> usersList = utilisateurService.getUsers();
        return usersList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UtilisateurDTO createUser(@RequestBody UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = convertToEntity(utilisateurDTO);
        Utilisateur utilisateurCreated = utilisateurService.saveUser(utilisateur);
        return convertToDto(utilisateurCreated);
    }

    @PutMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id ,@RequestBody UtilisateurDTO utilisateurDTO) {
        try {
            Utilisateur utilisateur = convertToEntity(utilisateurDTO);
            Utilisateur updatedUser = utilisateurService.getUserById(id).get();

            updatedUser.setNom(utilisateur.getNom());
            updatedUser.setPrenom(utilisateur.getPrenom());
            updatedUser.setEmail(utilisateur.getEmail());
            updatedUser.setDate_de_naissance(utilisateur.getDate_de_naissance());

            utilisateurService.updateUser(updatedUser);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }

    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public UtilisateurDTO getSpecificUser(@PathVariable("id") Long id) {
        try {
            return convertToDto(utilisateurService.getUserById(id).get());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
    }


    private Utilisateur convertToEntity(UtilisateurDTO utilisateurDTO) {
        return modelMapper.map(utilisateurDTO, Utilisateur.class);
    }

    private UtilisateurDTO convertToDto(Utilisateur utilisateur) {
        return modelMapper.map(utilisateur, UtilisateurDTO.class);
    }

}
