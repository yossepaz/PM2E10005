package com.example.pm2e10005.configuracion;
public class Transacciones {

    /*Nombre de la base de datos*/
    public static final String NameDatabase ="PM01DB";

    /* Tablas de la base de datos en SQLite*/

    public static final  String tablaContacto= "contacto";

    /*Campos de la tabla personas de la base de datos en SQLite*/

    public static final String id="id";
    public static final String nombres="nombres";
    public static final String telefono="telefono";
    public static final String pais="pais";
    public static final String nota="nota";



    /*Transacciones DDl tabla Contacto ""  = * + -*/

    public static final String createTableContacto ="CREATE TABLE contacto (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "pais TEXT, nombres TEXT, telefono TEXT, nota TEXT)";

    public static final String DROPTableContacto = "DROP TABLE EXISTS contacto";



}
