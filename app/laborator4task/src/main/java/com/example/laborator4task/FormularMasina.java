package com.example.laborator4task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularMasina extends AppCompatActivity {

    private EditText etMarca;
    private EditText etAn;
    private EditText etPret;
    private CheckBox cbEsteElectrica;

    private Spinner spnCaroserie;
    private RadioGroup grCombustibil;
    private Switch swAreGarantie;
    private RatingBar rbRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular_masina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //leg vb Java de componentele xml prin iduri
        etMarca = findViewById(R.id.et_marca);
        etAn = findViewById(R.id.et_an);
        etPret = findViewById(R.id.et_pret);
        cbEsteElectrica = findViewById(R.id.cb_este_electrica);

        spnCaroserie = findViewById(R.id.spn_caroserie);
        grCombustibil = findViewById(R.id.rg_combustibil);
        swAreGarantie = findViewById(R.id.sw_are_garantie);
        rbRating = findViewById(R.id.rb_rating);
    }

    public void salveazaMasina(View view) {
        //extrag valorile introduse de user in acele campuri
        String marca = etMarca.getText().toString().trim();
        String anDarString = etAn.getText().toString().trim();
        String pretDarString = etPret.getText().toString().trim();

        if (marca.isEmpty() || pretDarString.isEmpty()) {
            Toast.makeText(this, "Completați marca și prețul!", Toast.LENGTH_SHORT).show();
            return;
        }

        int an = Integer.parseInt(anDarString);
        double pret = Double.parseDouble(pretDarString);

        //extrag modelul selectat din Spinner
        String model = spnCaroserie.getSelectedItem().toString();

        //citesc din RadioGroup - aflu care buton e bifat si pe ala il salvez
        int idRadioButtonSelectat = grCombustibil.getCheckedRadioButtonId();
        RadioButton rbSelectat = findViewById(idRadioButtonSelectat);
        String tipCombustibil = rbSelectat.getText().toString();

        //citesc din CheckBox, Switch si RatingBar
        Boolean esteElectrica = cbEsteElectrica.isChecked();
        boolean areGarantie = swAreGarantie.isChecked();
        float rating = rbRating.getRating();

        //construiesc obiectul - instantiez cls MasinaAuto
        MasinaAuto masinaNoua = new MasinaAuto(
                model,
                pret,
                areGarantie,
                tipCombustibil,
                marca,
                rating,
                esteElectrica
        );



        //trimit obiectul inapoi in prima fereasta, in main
        Intent it = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("masinaAdaugata", masinaNoua);
        it.putExtras(bundle);

        setResult(RESULT_OK, it);
        finish();//inchide activitatea curenta si se intoarce la main


    }
}