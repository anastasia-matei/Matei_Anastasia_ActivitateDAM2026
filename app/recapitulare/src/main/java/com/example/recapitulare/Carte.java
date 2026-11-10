package com.example.recapitulare;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Carte implements Parcelable {

    private String titlu;
    private int numarPagini;
    private boolean areCopertaDura; //checkbox
    private String format; //RadioGroup (Fizic / E-Book / Audio)
    private String gen; //Spinner (Fictiune, Istorie, Tehnologie)

    private float rating; //rating bar

    private boolean esteImprumutata; //switch

    public Carte() {
    }

    public Carte(String titlu, int numarPagini, boolean areCopertaDura, String format, String gen, float rating, boolean esteImprumutata) {
        this.titlu = titlu;
        this.numarPagini = numarPagini;
        this.areCopertaDura = areCopertaDura;
        this.format = format;
        this.gen = gen;
        this.rating = rating;
        this.esteImprumutata = esteImprumutata;
    }

    protected Carte(Parcel in) {
        titlu = in.readString();
        numarPagini = in.readInt();
        areCopertaDura = in.readByte() != 0;
        format = in.readString();
        gen = in.readString();
        rating = in.readFloat();
        esteImprumutata = in.readByte() != 0;
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public String getTitlu() {
        return titlu;
    }
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getNumarPagini() {
        return numarPagini;
    }
    public void setNumarPagini(int numarPagini) {
        this.numarPagini = numarPagini;
    }

    public boolean isAreCopertaDura() {
        return areCopertaDura;
    }
    public void setAreCopertaDura(boolean areCopertaDura) {
        this.areCopertaDura = areCopertaDura;
    }

    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }

    public String getGen() {
        return gen;
    }
    public void setGen(String gen) {
        this.gen = gen;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isEsteImprumutata() {
        return esteImprumutata;
    }
    public void setEsteImprumutata(boolean esteImprumutata) {
        this.esteImprumutata = esteImprumutata;
    }

    @Override
    public String toString() {
        return
                "Titlu: '" + titlu + '\'' +
                "\nNumar pagini:" + numarPagini +
                "\nAre coperta dura: " + areCopertaDura +
                "\nFormat: " + format  +
                "\nGen: " + gen  +
                "\n Rating:" + rating +
                "\n E imprumutata: " + esteImprumutata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(titlu);
        dest.writeInt(numarPagini);
        dest.writeByte((byte) (areCopertaDura ? 1 : 0));
        dest.writeString(format);
        dest.writeString(gen);
        dest.writeFloat(rating);
        dest.writeByte((byte) (esteImprumutata ? 1 : 0));
    }
}
