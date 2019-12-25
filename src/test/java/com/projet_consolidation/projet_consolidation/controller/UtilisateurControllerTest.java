package com.projet_consolidation.projet_consolidation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet_consolidation.projet_consolidation.model.Utilisateur;
import com.projet_consolidation.projet_consolidation.service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        utilisateur = new Utilisateur( 1001,"Mohamed", "bizid", "Mohamed.bizid@hotmail.com", LocalDate.of(2009,04,26));
    }

    @Test
    public void getAllUsers() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(new Utilisateur("walid", "bizid", "walid.bizid@hotmail.com", LocalDate.of(1994,05,29)));
        utilisateurs.add(new Utilisateur("Majdi", "bizid", "majdi.bizid@hotmail.com", LocalDate.of(1997,05,25)));
        Page<Utilisateur> pagedUsers = new PageImpl(utilisateurs);
        Pageable pageRequest = PageRequest.of(0, 4);
        when(utilisateurService.getAllUsers(pageRequest)).thenReturn(pagedUsers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void createdUserSuccessfully() throws Exception {
        when(utilisateurService.saveUser(any(Utilisateur.class))).thenReturn(utilisateur);

        ObjectMapper objectMapper = new ObjectMapper();
        String utilisateurJSON = objectMapper.writeValueAsString(utilisateur);

        ResultActions result = mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(utilisateurJSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("bizid"))
                .andExpect(jsonPath("$.prenom").value("Mohamed"));
    }

    @Test
    public void deleteUserSuccessfully() throws Exception {
        when(utilisateurService.saveUser(any(Utilisateur.class))).thenReturn(utilisateur);
        doNothing().when(utilisateurService).deleteUser(utilisateur.getId());
        mockMvc.perform(delete("/api/v1/user/{userId}",1351))
                .andExpect(content().string("user deleted successfully"));
    }
}
