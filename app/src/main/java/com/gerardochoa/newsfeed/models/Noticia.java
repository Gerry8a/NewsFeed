package com.gerardochoa.newsfeed.models;

public class Noticia {

    private String titulo, fuente, fechaPublicacion, imgNoticia;

    public Noticia(String titulo, String fuente, String fechaPublicacion, String imgNoticia) {
        this.titulo = titulo;
        this.fuente = fuente;
        this.fechaPublicacion = fechaPublicacion;
        this.imgNoticia = imgNoticia;
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

    public String getImgNoticia() {
        return imgNoticia;
    }

    public void setImgNoticia(String imgNoticia) {
        this.imgNoticia = imgNoticia;
    }
}
