package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Paiement extends PanacheEntity {
    public String merchant;
    public int typ;
    public long idUtilisateur;

    public String date;
    public String service;
    public String reference;
    public String phone;
    public double amount;
    public String currency;
    public String callbackurl;
    public boolean valider;
}
