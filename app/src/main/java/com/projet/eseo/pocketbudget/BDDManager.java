package com.projet.eseo.pocketbudget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by marc-antoine on 02/02/15.
 */

public class BDDManager {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "budget.db";

    private static final String TABLE_DEPENSES = "table_depenses";
    private static final String TABLE_REVENUS = "table_revenus";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_DATE = "DATE";
    private static final int NUM_COL_DATE = 1;
    private static final String COL_CATEGORIE = "CATEGORIE";
    private static final int NUM_COL_CATEGORIE = 2;
    private static final String COL_NOM = "NOM";
    private static final int NUM_COL_NOM = 3;
    private static final String COL_MONTANT = "MONTANT";
    private static final int NUM_COL_MONTANT = 4;

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

    public long insertDepense(Depense depense){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, depense.getCategorie());
        values.put(COL_DATE, dateFormat.format(depense.getDate()));
        values.put(COL_NOM, depense.getNom());
        values.put(COL_MONTANT, depense.getMontant());
        return bdd.insert(TABLE_DEPENSES, null, values);
    }

    public long insertRevenu(Revenu revenus){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, revenus.getCategorie());
        values.put(COL_DATE, dateFormat.format(revenus.getDate()));
        values.put(COL_NOM, revenus.getNom());
        values.put(COL_MONTANT, revenus.getMontant());
        return bdd.insert(TABLE_REVENUS, null, values);
    }

    public int updateDepense(int id, Depense depense){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE,depense.getCategorie());
        values.put(COL_DATE,dateFormat.format(depense.getDate()));
        values.put(COL_NOM, depense.getNom());
        values.put(COL_MONTANT,depense.getMontant());
        return bdd.update(TABLE_DEPENSES, values, COL_ID + " = " +id, null);
    }

    public int updateRevenus(int id, Revenu revenu){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE,revenu.getCategorie());
        values.put(COL_DATE,dateFormat.format(revenu.getDate()));
        values.put(COL_NOM, revenu.getNom());
        values.put(COL_MONTANT,revenu.getMontant());
        return bdd.update(TABLE_REVENUS, values, COL_ID + " = " +id, null);
    }

    public int removeDepenseWithID(int id){
        return bdd.delete(TABLE_DEPENSES, COL_ID + " = " +id, null);
    }

    public int removeRevenuWithID(int id){
        return bdd.delete(TABLE_DEPENSES, COL_ID + " = " +id, null);
    }

    public Depense getDepenseWithNom(String nom){
        Cursor c = bdd.query(TABLE_DEPENSES, new String[] {COL_ID, COL_CATEGORIE, COL_DATE, COL_NOM,COL_MONTANT}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToDepense(c);
    }

    public Revenu getRevenuWithNom(String nom){
        Cursor c = bdd.query(TABLE_REVENUS, new String[] {COL_ID, COL_CATEGORIE, COL_DATE, COL_NOM,COL_MONTANT}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToRevenu(c);
    }

    public ArrayList<Depense> getAllDepense(){
        ArrayList<Depense> depenses= new ArrayList<>();
        Cursor cursor=bdd.rawQuery("Select * from Depense",null);

        if(cursor.moveToFirst()){
            while(cursor.isAfterLast()==false){
                Depense depense= new Depense();
                depense.setNom(cursor.getString(NUM_COL_NOM));
                depense.setMontant(cursor.getFloat(NUM_COL_MONTANT));
                depenses.add(depense);
                cursor.moveToNext();
            }
        }
        return depenses;
    }

    public ArrayList<Revenu> getAllRevenus(){
        ArrayList<Revenu> revenus= new ArrayList<>();
        Cursor cursor=bdd.rawQuery("Select * from Revenu",null);

        if(cursor.moveToFirst()){
            while(cursor.isAfterLast()==false){
                Revenu revenu= new Revenu();
                revenu.setNom(cursor.getString(NUM_COL_NOM));
                revenu.setMontant(cursor.getFloat(NUM_COL_MONTANT));
                revenus.add(revenu);
                cursor.moveToNext();
            }
        }
        return revenus;
    }

    private Depense cursorToDepense(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        Depense depense = new Depense();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        depense.setId(c.getInt(NUM_COL_ID));

        depense.setNom(c.getString(NUM_COL_NOM));
        depense.setCategorie(c.getString(NUM_COL_CATEGORIE));
        depense.setMontant(c.getFloat(NUM_COL_MONTANT));
        //On ferme le cursor
        c.close();

        return depense;
    }

    private Revenu cursorToRevenu(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        Revenu revenu = new Revenu();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        revenu.setId(c.getInt(NUM_COL_ID));

        revenu.setNom(c.getString(NUM_COL_NOM));
        revenu.setCategorie(c.getString(NUM_COL_CATEGORIE));
        revenu.setMontant(c.getFloat(NUM_COL_MONTANT));
        //On ferme le cursor
        c.close();

        return revenu;
    }

}
