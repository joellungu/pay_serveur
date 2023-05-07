package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Produit extends PanacheEntity {

    public long idBoutique;
    public String nom;
    public String type;
    public double prix;
    public String devise;
    public int quantite;
    public double poids;
    public String unite;
    public String details;
    public byte[] photo;
    public boolean valide;
    public String date;
}
