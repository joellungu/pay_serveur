package org.acme.models;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Boutique extends PanacheEntity {
    public String nom;
    public String postnom;
    public String prenom;
    public String sexe;
    public String etatCivil;
    public String email;
    public String telephone;
    public String adresse;
    public String denomination;
    public String adresseEtablissement;
    public String rccm;
    public String idnat;
    public String numeroImpot;
    public String provinceSiege;
    public String typeEtablissement;
    public String categorie;
    public int nombreEtablissement;
    public byte[] photo;
    public String codePromo;
    public String motDePasse;
    public int status;
    public String code;
    public String token;

}
