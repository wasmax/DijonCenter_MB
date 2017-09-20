package com.example.barthelemy.dijoncenter_mb.Model;

import java.util.Date;

/**
 * Created by Max on 20/09/2017.
 */

public class Parcours {
    String idCinema;
    String idRestaurant;
    String dateCreation;
    String dateRealisation;
    String commentaire;
    String statut;

    public Parcours(String idCinema, String idRestaurant, String dateCreation, String dateRealisation, String commentaire, String statut) {
        this.idCinema = idCinema;
        this.idRestaurant = idRestaurant;
        this.dateCreation = dateCreation;
        this.dateRealisation = dateRealisation;
        this.commentaire = commentaire;
        this.statut = statut;
    }

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
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
