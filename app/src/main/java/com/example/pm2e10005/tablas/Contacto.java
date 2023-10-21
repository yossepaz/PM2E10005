package com.example.pm2e10005.tablas;

public class Contacto {

    private  Integer id;
    private String pais;
    private String nombres;
    private String telefono;
    private String nota;
    //contructor de clase
    public Contacto(Integer id,String pais, String nombres, String telefono, String nota) {
        this.id = id;
        this.pais=pais;
        this.nombres = nombres;
        this.telefono = telefono;
        this.nota = nota;
    }
    //Segundo Constructor de clase:
    public  Contacto(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}