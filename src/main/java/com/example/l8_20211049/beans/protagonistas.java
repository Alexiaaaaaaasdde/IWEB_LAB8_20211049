package com.example.l8_20211049.beans;

public class protagonistas {

    private int idPelicula;

    private int idActor;

    public protagonistas(int idPelicula, int idActor) {
        this.idPelicula = idPelicula;
        this.idActor = idActor;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }
}
