package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Entreprise extends PanacheEntity {
    public String nom;
    public String adresse;
    public String email;
    public String telephone;
    public String motdepasse;
    public String activite;
    public String codePromo;

}
