package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Utilisateur extends PanacheEntity {
    public String nomUtilisateur;
    public String telephone;
    public String motdepasse;
    public String codePromo;
    public byte[] photo;
}
