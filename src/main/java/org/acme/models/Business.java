package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Business extends PanacheEntity {
    public long idBoutique;
    public String adresse;
    public String nomUtilisateur;
    public String nomBusiness;
    public String mdp;
    public int role;
    public byte[] photo;
}
