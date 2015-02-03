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

    public long insertDepense(Expenditure expenditure){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, expenditure.getCategorie());
        values.put(COL_DATE, dateFormat.format(expenditure.getDate()));
        values.put(COL_NOM, expenditure.getNom());
        values.put(COL_MONTANT, expenditure.getMontant());
        return bdd.insert(TABLE_DEPENSES, null, values);
    }

    public long insertRevenu(Income revenus){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, revenus.getCategorie());
        values.put(COL_DATE, dateFormat.format(revenus.getDate()));
        values.put(COL_NOM, revenus.getNom());
        values.put(COL_MONTANT, revenus.getMontant());
        return bdd.insert(TABLE_REVENUS, null, values);
    }

    public int updateDepense(int id, Expenditure expenditure){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, expenditure.getCategorie());
        values.put(COL_DATE,dateFormat.format(expenditure.getDate()));
        values.put(COL_NOM, expenditure.getNom());
        values.put(COL_MONTANT, expenditure.getMontant());
        return bdd.update(TABLE_DEPENSES, values, COL_ID + " = " +id, null);
    }

    public int updateRevenus(int id, Income income){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIE, income.getCategorie());
        values.put(COL_DATE,dateFormat.format(income.getDate()));
        values.put(COL_NOM, income.getNom());
        values.put(COL_MONTANT, income.getMontant());
        return bdd.update(TABLE_REVENUS, values, COL_ID + " = " +id, null);
    }

    public int removeDepenseWithID(int id){
        return bdd.delete(TABLE_DEPENSES, COL_ID + " = " +id, null);
    }

    public int removeRevenuWithID(int id){
        return bdd.delete(TABLE_DEPENSES, COL_ID + " = " +id, null);
    }

    public Expenditure getDepenseWithNom(String nom){
        Cursor c = bdd.query(TABLE_DEPENSES, new String[] {COL_ID, COL_CATEGORIE, COL_DATE, COL_NOM,COL_MONTANT}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToDepense(c);
    }

    public Income getRevenuWithNom(String nom){
        Cursor c = bdd.query(TABLE_REVENUS, new String[] {COL_ID, COL_CATEGORIE, COL_DATE, COL_NOM,COL_MONTANT}, COL_NOM + " LIKE \"" + nom +"\"", null, null, null, null);
        return cursorToRevenu(c);
    }

    public ArrayList<Expenditure> getAllDepense(){
        ArrayList<Expenditure> expenditures = new ArrayList<>();
        Cursor cursor=bdd.rawQuery("Select * from Expenditure",null);

        if(cursor.moveToFirst()){
            do{
                Expenditure expenditure = new Expenditure();
                expenditure.setNom(cursor.getString(NUM_COL_NOM));
                expenditure.setMontant(cursor.getFloat(NUM_COL_MONTANT));
                expenditures.add(expenditure);
                cursor.moveToNext();
            }while(cursor.isAfterLast()==false);
        }
        return expenditures;
    }

    public ArrayList<Income> getAllRevenus(){
        ArrayList<Income> incomes = new ArrayList<>();
        Cursor cursor=bdd.rawQuery("Select * from Income",null);

        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                Income income = new Income();
                income.setNom(cursor.getString(NUM_COL_NOM));
                income.setMontant(cursor.getFloat(NUM_COL_MONTANT));
                incomes.add(income);
                cursor.moveToNext();
            }
        }
        return incomes;
    }

    private Expenditure cursorToDepense(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        Expenditure expenditure = new Expenditure();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        expenditure.setId(c.getInt(NUM_COL_ID));

        expenditure.setNom(c.getString(NUM_COL_NOM));
        expenditure.setCategorie(c.getString(NUM_COL_CATEGORIE));
        expenditure.setMontant(c.getFloat(NUM_COL_MONTANT));
        //On ferme le cursor
        c.close();

        return expenditure;
    }

    private Income cursorToRevenu(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        Income income = new Income();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        income.setId(c.getInt(NUM_COL_ID));

        income.setNom(c.getString(NUM_COL_NOM));
        income.setCategorie(c.getString(NUM_COL_CATEGORIE));
        income.setMontant(c.getFloat(NUM_COL_MONTANT));
        //On ferme le cursor
        c.close();

        return income;
    }

}
