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
    private int putere;
    private String dataITP;
    private String culoare;
    private int an;




    public MasinaAuto(String model, double pret, boolean areGarantie, String tipCombustibil, String marca, float rating, boolean esteElectrica, int putere, String dataITP, String culoare, int an) {
        this.model = model;
        this.pret = pret;
        this.areGarantie = areGarantie;
        this.tipCombustibil = tipCombustibil;
        this.marca = marca;
        this.rating = rating;
        this.esteElectrica = esteElectrica;
        this.putere = putere;
        this.dataITP=dataITP;
        this.culoare=culoare;
        this.an=an;
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

    public int getPutere() {
        return putere;
    }

    public void setPutere(int putere) {
        this.putere = putere;
    }

    public String getDataITP() {
        return dataITP;
    }

    public void setDataITP(String dataITP) {
        this.dataITP = dataITP;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    @Override
    public String toString() {
        return marca + " " + model + "\n" +
                "-> Pret: " + pret + " EUR\n" +
                "-> Combustibil: " + tipCombustibil + "\n" +
                "-> An Fabricatie: " + an + "\n" +
                "-> Putere: " + putere + " CP"+ "\n" +
                "-> Electrica: " + (esteElectrica ? "Da" : "Nu") + "\n" +
                "-> Data ITP: " + dataITP + "\n" +
                "-> Garantie: " + (areGarantie ? "Da" : "Nu") + "\n" +
                "-> Culoare: " + culoare + "\n"+
                "-> Rating: " + rating + " / 5 ★";
    }
}
