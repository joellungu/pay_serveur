package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public class Commande extends PanacheEntity {
    public long idBoutique;
    public long idBusiness;
    public long idUtilisateur;
    public String date;
    public int type;//1 pour commande normal et 2 pour evenement
    public long idProduit;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<HashMap> produits;
    public double prix;
    public String devise;
    public String reference;
    public int tables;
    public boolean deja;//Pour les evenements
}
