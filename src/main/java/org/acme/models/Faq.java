package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Faq extends PanacheEntity {
    public String question;
    public String reponse;
}
