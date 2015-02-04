package com.projet.eseo.pocketbudget;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by marc-antoine on 02/02/15.
 */
public class BaseSQLite extends SQLiteOpenHelper{

    private static final String COL_ID="ID";
    private static final String TABLE_DEPENSES= "table_depenses";
    private static final String TABLE_REVENUS= "table_revenus";
    private static final String COL_DATE="DATE";
    private static final String COL_NOM= "NOM";
    private static final String COL_CATEGORIE="CATEGORIE";
    private static final String COL_MONTANT= "MONTANT";

    private static final String CREATE_TABLE_DEPENSES = "CREATE TABLE " + TABLE_DEPENSES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT NOT NULL,"
            + COL_CATEGORIE+" TEXT NOT NULL,"+COL_NOM+" TEXT NOT NULL,"+COL_MONTANT+" TEXT NOT NULL);";
    private static final String CREATE_TABLE_REVENUS = " CREATE TABLE "+ TABLE_REVENUS+ " ( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT NOT NULL,"
            + COL_CATEGORIE+" TEXT NOT NULL,"+COL_NOM+" TEXT NOT NULL,"+COL_MONTANT+" TEXT NOT NULL);";


    public BaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE_DEPENSES);
        db.execSQL(CREATE_TABLE_REVENUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_DEPENSES + ";");
        db.execSQL("DROP TABLE "+TABLE_REVENUS+ ";");
        onCreate(db);
    }
}
