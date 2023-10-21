package com.example.pm2e10005.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteConexion extends SQLiteOpenHelper {

    private String nombres;
    private String pais;
    private String nota;
    private String telefono;



    //constructor de clase cracion de la base de datos en sqlite
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version ){
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Creacion de la primera tabla de la base de datos*/
        db.execSQL(Transacciones.createTableContacto);
        //db.execSQL(TransaccionesPais.createTablePais);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Eliminacion de las tablas al momento de eliminar la informacion de la db
        db.execSQL(Transacciones.DROPTableContacto);
        //db.execSQL(TransaccionesPais.DROPTablePais);
        onCreate(db);

    }
}
