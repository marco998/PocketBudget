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
    private static final String TABLE_REVENUS= "table_depenses";

    private static final String COL_NOM= "NOM";
    private static final String COL_CATEGORIE="CATEGORIE";
    private static final String COL_MONTANT= "MONTANT";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_DEPENSES + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DEPENSE + " TEXT NOT NULL);" +
            "CREATE TABLE "+ TABLE_REVENUS+"("+ COL_ID+ "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_REVENUS+ "TEXT NOT NULL);"  ;

    public BaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_DEPENSES + ";");
        onCreate(db);
    }
}
