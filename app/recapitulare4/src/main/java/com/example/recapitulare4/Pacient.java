package com.example.recapitulare4;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Pacient implements Parcelable {
    private int codPacient;//edittext
    private String sex;//radiogroup
    private int greutate;//seekbar
    private boolean eUrgenta;//checkbox
    private String grupaSanguina;//spinner
    private float nivelDurere;//ratingbar

    public Pacient() {
    }

    public Pacient(int codPacient, String sex, int greutate, boolean eUrgenta, String grupaSanguina, float nivelDurere) {
        this.codPacient = codPacient;
        this.sex = sex;
        this.greutate = greutate;
        this.eUrgenta = eUrgenta;
        this.grupaSanguina = grupaSanguina;
        this.nivelDurere = nivelDurere;
    }

    protected Pacient(Parcel in) {
        codPacient = in.readInt();
        sex = in.readString();
        greutate = in.readInt();
        eUrgenta = in.readByte() != 0;
        grupaSanguina = in.readString();
        nivelDurere = in.readFloat();
    }

    public static final Creator<Pacient> CREATOR = new Creator<Pacient>() {
        @Override
        public Pacient createFromParcel(Parcel in) {
            return new Pacient(in);
        }

        @Override
        public Pacient[] newArray(int size) {
            return new Pacient[size];
        }
    };

    public int getCodPacient() {
        return codPacient;
    }

    public void setCodPacient(int codPacient) {
        this.codPacient = codPacient;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGreutate() {
        return greutate;
    }

    public void setGreutate(int greutate) {
        this.greutate = greutate;
    }

    public boolean iseUrgenta() {
        return eUrgenta;
    }

    public void seteUrgenta(boolean eUrgenta) {
        this.eUrgenta = eUrgenta;
    }

    public String getGrupaSanguina() {
        return grupaSanguina;
    }

    public void setGrupaSanguina(String grupaSanguina) {
        this.grupaSanguina = grupaSanguina;
    }

    public float getNivelDurere() {
        return nivelDurere;
    }

    public void setNivelDurere(float nivelDurere) {
        this.nivelDurere = nivelDurere;
    }

    @Override
    public String toString() {
        return
                "codPacient: " + codPacient +
                "\nsex: " + sex +
                "\ngreutate: " + greutate +
                "\neUrgenta: " + eUrgenta +
                "\ngrupaSanguina: " + grupaSanguina  +
                "\nnivelDurere: " + nivelDurere ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(codPacient);
        dest.writeString(sex);
        dest.writeInt(greutate);
        dest.writeByte((byte) (eUrgenta ? 1 : 0));
        dest.writeString(grupaSanguina);
        dest.writeFloat(nivelDurere);
    }
}
