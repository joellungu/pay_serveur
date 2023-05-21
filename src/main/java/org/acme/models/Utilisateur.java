package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.HashMap;

@Entity
public class Utilisateur extends PanacheEntity {
    public long idUtilisateur;
    public HashMap utilisateur;
}
