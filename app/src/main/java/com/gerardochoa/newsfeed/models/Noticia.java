package com.gerardochoa.newsfeed.models;

public class Noticia {

    private String titulo, fuente, fechaPublicacion;
    private int imgNoticia;

    public Noticia(String titulo, String fuente, String fechaPublicacion) {
        this.titulo = titulo;
        this.fuente = fuente;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
