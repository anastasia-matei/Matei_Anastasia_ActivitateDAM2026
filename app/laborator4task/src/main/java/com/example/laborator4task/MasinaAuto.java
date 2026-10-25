package com.example.laborator4task;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MasinaAuto implements Serializable {
    private double pret;
    private String marca;
    private String model;
    private boolean esteElectrica;
    private boolean areGarantie;
    private String tipCombustibil;
    private float rating;



    public MasinaAuto(String model, double pret, boolean areGarantie, String tipCombustibil, String marca, float rating, boolean esteElectrica) {
        this.model = model;
        this.pret = pret;
        this.areGarantie = areGarantie;
        this.tipCombustibil = tipCombustibil;
        this.marca = marca;
        this.rating = rating;
        this.esteElectrica = esteElectrica;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public double getPret() {
        return pret;
    }
    public void setPret(double pret) {
        this.pret = pret;
    }

    public boolean isAreGarantie() {
        return areGarantie;
    }
    public void setAreGarantie(boolean areGarantie) {
        this.areGarantie = areGarantie;
    }

    public String getTipCombustibil() {
        return tipCombustibil;
    }
    public void setTipCombustibil(String tipCombustibil) {
        this.tipCombustibil = tipCombustibil;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isEsteElectrica() {
        return esteElectrica;
    }
    public void setEsteElectrica(boolean esteElectrica) {
        this.esteElectrica = esteElectrica;
    }

    @Override
    public String toString() {
        return marca + " " + model + "\n" +
                "-> Preț: " + pret + " EUR\n" +
                "-> Combustibil: " + tipCombustibil + "\n" +
                "-> Electrică: " + (esteElectrica ? "Da" : "Nu") + "\n" +
                "-> Garanție: " + (areGarantie ? "Da" : "Nu") + "\n" +
                "-> Rating: " + rating + " / 5 ★";
    }
}
