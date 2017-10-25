package com.example.barthelemy.dijoncenter_mb.Model;

import java.util.Date;

/**
 * Created by Max on 20/09/2017.
 */

public class Parcours {
    String nomCinema;
    String nomRestaurant;
    String dateCreation;
    String dateRealisation;
    String commentaire;
    String statut;

    public Parcours(String nomCinema, String nomRestaurant, String dateCreation, String dateRealisation, String commentaire, String statut) {
        this.nomCinema = nomCinema;
        this.nomRestaurant = nomRestaurant;
        this.dateCreation = dateCreation;
        this.dateRealisation = dateRealisation;
        this.commentaire = commentaire;
        this.statut = statut;
    }

    public String getIdCinema() {
        return nomCinema;
    }

    public void setIdCinema(String idCinema) {
        this.nomCinema = idCinema;
    }

    public String getIdRestaurant() {
        return nomRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.nomRestaurant = idRestaurant;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(String dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
