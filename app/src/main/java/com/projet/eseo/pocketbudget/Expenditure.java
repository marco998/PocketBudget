package com.projet.eseo.pocketbudget;

import java.util.Date;

/**
 * Created by marc-antoine on 02/02/15.
 * Modèle des dépenses
 */
public class Expenditure {

    private long id;
    private String date;
    private String categorie;
    private String nom;
    private float montant;

    public Expenditure(){}

    public Expenditure(String date, String categorie, String nom, float montant){
        this.date=date;
        this.categorie=categorie;
        this.nom=nom;
        this.montant=montant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
