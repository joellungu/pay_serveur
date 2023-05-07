package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Agent extends PanacheEntity {
    public String nom;
    public String postnom;
    public String prenom;
    public String email;
    public String telephone;
    public String adresse;
    public String sexe;
    public String dateNaissance;
    public boolean suspendu;
    public String code;

}
