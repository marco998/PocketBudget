package com.projet.eseo.pocketbudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by marc-antoine on 02/02/15.
 */

public class BDDManager {

    private static final String NOM_BDD = "budget.db";
    private static final String TABLE_DEPENSES = "table_depenses";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ISBN = "ISBN";
    private static final int NUM_COL_ISBN = 1;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 2;

    private SQLiteDatabase bdd;

    private BaseSQLite BaseSQLite;

    public BDDManager(Context context){
        //On créer la BDD et sa table
        BaseSQLite = new BaseSQLite(context, NOM_BDD, null, VERSION_BDD);

    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = BaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertDepense(Livre livre){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL, livre.getIsbn());
        values.put(COL_TITRE, livre.getTitre());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_LIVRES, null, values);
    }
}
