package com.example.pm2e10005.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteConexion1 extends SQLiteOpenHelper {

    private int id;
    private String nombrePais;
    //private String area;



    //constructor de clase cracion de la base de datos en sqlite
    public SQLiteConexion1(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version ){
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Creacion de la primera tabla de la base de datos*/
        db.execSQL(TransaccionesPais.createTablePais);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i,int newVersion) {
        //Eliminacion de las tablas al momento de eliminar la informacion de la db
        db.execSQL(TransaccionesPais.DROPTablePais);
        onCreate(db);

    }
}
