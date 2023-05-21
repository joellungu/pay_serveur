package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Utilisateur extends PanacheEntity {
    public String idUtilisateur;
    public String nom;
    public String email;
    public String photoURL;

}
