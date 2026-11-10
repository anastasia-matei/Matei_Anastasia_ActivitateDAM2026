package com.example.recapitulare3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Smartwatch implements Parcelable {
    private int idCeas;//edittext
    private String producator;//radiogroup
    private int capacitateBaterie;//seekbar
    private boolean areNFC;//checkbox
    private String materialCurea;//spinner
    private float rating;//rating bar

    public Smartwatch() {
    }

    public Smartwatch(int idCeas, String producator, int capacitateBaterie, boolean areNFC, String materialCurea, float rating) {
        this.idCeas = idCeas;
        this.producator = producator;
        this.capacitateBaterie = capacitateBaterie;
        this.areNFC = areNFC;
        this.materialCurea = materialCurea;
        this.rating = rating;
    }

    protected Smartwatch(Parcel in) {
        idCeas = in.readInt();
        producator = in.readString();
        capacitateBaterie = in.readInt();
        areNFC = in.readByte() != 0;
        materialCurea = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<Smartwatch> CREATOR = new Creator<Smartwatch>() {
        @Override
        public Smartwatch createFromParcel(Parcel in) {
            return new Smartwatch(in);
        }

        @Override
        public Smartwatch[] newArray(int size) {
            return new Smartwatch[size];
        }
    };

    public int getIdCeas() {
        return idCeas;
    }

    public void setIdCeas(int idCeas) {
        this.idCeas = idCeas;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getCapacitateBaterie() {
        return capacitateBaterie;
    }

    public void setCapacitateBaterie(int capacitateBaterie) {
        this.capacitateBaterie = capacitateBaterie;
    }

    public boolean isAreNFC() {
        return areNFC;
    }

    public void setAreNFC(boolean areNFC) {
        this.areNFC = areNFC;
    }

    public String getMaterialCurea() {
        return materialCurea;
    }

    public void setMaterialCurea(String materialCurea) {
        this.materialCurea = materialCurea;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return
                "ID ceas: " + idCeas +
                "\nProducator: " + producator +
                "\nCapacitate Baterie: " + capacitateBaterie +
                "\nAre NFC? " + areNFC +
                "\nMaterial Curea: " + materialCurea+
                "\nRating: "+rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idCeas);
        dest.writeString(producator);
        dest.writeInt(capacitateBaterie);
        dest.writeByte((byte) (areNFC ? 1 : 0));
        dest.writeString(materialCurea);
        dest.writeFloat(rating);
    }
}
