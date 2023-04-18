package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Compte extends PanacheEntity {
    public String reseau;
    public String numero;
    public long idProprietaire;
}
