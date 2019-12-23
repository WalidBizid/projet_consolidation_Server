package com.projet_consolidation.projet_consolidation.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur extends AuditModel {

    @Id
    @GeneratedValue(generator = "utilisateurs_generator")
    @SequenceGenerator(
            name = "utilisateurs_generator",
            sequenceName = "utilisateurs_generator",
            initialValue = 1000
    )
    private Long id;

    @Column(
            name = "prenom",
            nullable = false
    )
    private String prenom;

    @Column(
            name = "nom",
            nullable = false
    )
    private String nom;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "date_de_naissance",
            nullable = false
    )
    @Temporal(TemporalType.DATE)
    private Date date_de_naissance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
}
