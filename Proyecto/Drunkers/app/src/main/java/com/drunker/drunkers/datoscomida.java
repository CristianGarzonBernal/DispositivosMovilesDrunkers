package com.drunker.drunkers;

public class datoscomida {
    private int datos;
    private String titulo;
    private double valor;
    private int imagen;

    public datoscomida(int datos, String titulo, double valor,int imagen) {
        this.datos = datos;
        this.titulo = titulo;
        this.valor = valor;
        this.imagen = imagen;
    }

    public int getDatos() {
        return datos;
    }

    public void setDatos(int datos) {
        this.datos = datos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

   // public int getImagen() {
     //   return imagen;
    //}

   /* public void setImagen(int imagen) {
        this.imagen = imagen;
    }*/
}
