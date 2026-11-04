package com.example.laborator4task;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class MasinaAuto implements Parcelable {
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

    protected MasinaAuto(Parcel in) { //implementare pt Parcel
        pret = in.readDouble();
        marca = in.readString();
        model = in.readString();
        esteElectrica = in.readByte() != 0;
        areGarantie = in.readByte() != 0;
        tipCombustibil = in.readString();
        rating = in.readFloat();
        putere = in.readInt();
        dataITP = in.readString();
        culoare = in.readString();
        an = in.readInt();
    }

    public static final Creator<MasinaAuto> CREATOR = new Creator<MasinaAuto>() {
        @Override
        public MasinaAuto createFromParcel(Parcel in) {
            return new MasinaAuto(in);
        }

        @Override
        public MasinaAuto[] newArray(int size) {
            return new MasinaAuto[size];
        }
    };
    //end implementare pr Parcel

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(pret);
        dest.writeString(marca);
        dest.writeString(model);
        dest.writeByte((byte) (esteElectrica ? 1 : 0));
        dest.writeByte((byte) (areGarantie ? 1 : 0));
        dest.writeString(tipCombustibil);
        dest.writeFloat(rating);
        dest.writeInt(putere);
        dest.writeString(dataITP);
        dest.writeString(culoare);
        dest.writeInt(an);
    }
}
