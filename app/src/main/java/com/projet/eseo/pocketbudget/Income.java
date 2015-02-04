package com.projet.eseo.pocketbudget;

import java.util.Date;

/**
 * Created by marc-antoine on 02/02/15.
 */
public class Income {

    private long id;
    private Date date;
    private String categorie;
    private String nom;
    private float montant;

    public Income(){}

    public Income(Date date, String categorie, String nom, float montant){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
