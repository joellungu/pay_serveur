package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;

@Entity
public class Business extends PanacheEntity {
    public long idBoutique;
    public String adresse;
    public String nomUtilisateur;

    @ColumnDefault("810011234")
    public String telephone;
    public String nomBusiness;
    public String mdp;
    public int role;
    public byte[] photo;
}
