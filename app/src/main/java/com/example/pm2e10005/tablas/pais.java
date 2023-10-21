package com.example.pm2e10005.tablas;

public class pais {

    private  Integer id;
    //private String area;
    private String nombrePais;

    //contructor de clase
    public pais(Integer id,  String nombrePais) {
        this.id = id;
        //this.area=area;
        this.nombrePais= nombrePais;

    }
    //Segundo Constructor de clase:
    public pais(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idPais) {
        this.id = id;
    }

   /* public String getArea() {
        return area;
    }*/

    /*public void setArea(String area) {
        this.area = area;
    }*/

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}