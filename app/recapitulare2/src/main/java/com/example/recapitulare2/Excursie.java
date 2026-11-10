package com.example.recapitulare2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Excursie implements Parcelable {

    private String destinatie;//edittext
    private int pretPerPersoana; //seekbar
    private boolean bagajExtra;//checkbox
    private boolean pensionar;//switch
    private String anotimp;//radiogroup
    private float rating;//ratingbar
    private String locatie;//spinner

    public Excursie() {
    }

    public Excursie(String destinatie, int pretPerPersoana, boolean bagajExtra, boolean pensionar, String anotimp, float rating, String locatie) {
        this.destinatie = destinatie;
        this.pretPerPersoana = pretPerPersoana;
        this.bagajExtra = bagajExtra;
        this.pensionar = pensionar;
        this.anotimp = anotimp;
        this.rating = rating;
        this.locatie = locatie;
    }

    protected Excursie(Parcel in) {
        destinatie = in.readString();
        pretPerPersoana = in.readInt();
        bagajExtra = in.readByte() != 0;
        pensionar = in.readByte() != 0;
        anotimp = in.readString();
        rating = in.readFloat();
        locatie = in.readString();
    }

    public static final Creator<Excursie> CREATOR = new Creator<Excursie>() {
        @Override
        public Excursie createFromParcel(Parcel in) {
            return new Excursie(in);
        }

        @Override
        public Excursie[] newArray(int size) {
            return new Excursie[size];
        }
    };

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public int getPretPerPersoana() {
        return pretPerPersoana;
    }

    public void setPretPerPersoana(int pretPerPersoana) {
        this.pretPerPersoana = pretPerPersoana;
    }

    public boolean isBagajExtra() {
        return bagajExtra;
    }

    public void setBagajExtra(boolean bagajExtra) {
        this.bagajExtra = bagajExtra;
    }

    public boolean isPensionar() {
        return pensionar;
    }

    public void setPensionar(boolean pensionar) {
        this.pensionar = pensionar;
    }

    public String getAnotimp() {
        return anotimp;
    }

    public void setAnotimp(String anotimp) {
        this.anotimp = anotimp;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    @Override
    public String toString() {
        return
                "Destinatie: " + destinatie  +
                "\nPret/persoana: " + pretPerPersoana +
                "\n Ai bagaj extra/ " + bagajExtra +
                "\nEsti pensionar? " + pensionar +
                "\nAnotimp: " + anotimp  +
                "\nRating: " + rating +
                "\nLocatie: " + locatie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(destinatie);
        dest.writeInt(pretPerPersoana);
        dest.writeByte((byte) (bagajExtra ? 1 : 0));
        dest.writeByte((byte) (pensionar ? 1 : 0));
        dest.writeString(anotimp);
        dest.writeFloat(rating);
        dest.writeString(locatie);
    }
}
