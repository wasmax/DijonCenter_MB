package com.example.barthelemy.dijoncenter_mb.Model;

import java.util.Date;

/**
 * Created by Max on 04/12/2017.
 */

public class Personne {
    Integer id;
    String nom;
    String prenom;
    Integer age;
    Integer poids;
    Date dateMaj;
    String login;

    public Personne() {
    }

    public Personne(Integer id, String nom, String prenom, Integer age, Integer poids, Date dateMaj, String login) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.poids = poids;
        this.dateMaj = dateMaj;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPoids() {
        return poids;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

