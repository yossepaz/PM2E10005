package com.example.pm2e10005.configuracion;
public class TransaccionesPais {

    /*Nombre de la base de datos*/
    public static final String NameDatabase ="PM01DB2";

    /* Tablas de la base de datos en SQLite*/
    public static final String tablaPais="pais";


    //Campos tabla Pais
    public static final String id="id";
    public static final String nombrePais="nombrePais";
    //public static final String area="area";

    /*Transacciones DDl tabla Pais ""  = * + -*/

    public static final String createTablePais ="CREATE TABLE pais (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombrePais TEXT)";

    public static final String DROPTablePais = "DROP TABLE EXISTS pais";


}
