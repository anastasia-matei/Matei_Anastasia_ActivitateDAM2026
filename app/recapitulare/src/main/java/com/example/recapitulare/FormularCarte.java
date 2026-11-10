package com.example.recapitulare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularCarte extends AppCompatActivity {

    private EditText etTitlu;
    private TextView tvNrPagini;
    private SeekBar sbNrPagini;
    private CheckBox cbAreCopertaDura;
    private RadioGroup rgFormat;
    private Spinner spnGen;
    private RatingBar rbRating;
    private Switch swEsteImprumutata;

    private Button btnSalveaza;

    private final String[] genuriCarte={"Fictiune", "Romantic", "Istoric", "Razboi", "Aventura", "SF", "Filosofie"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitlu = findViewById(R.id.et_titlu_carte);
        tvNrPagini=findViewById(R.id.tv_nr_pagini_afisat);
        sbNrPagini=findViewById(R.id.sb_nr_pagini);
        cbAreCopertaDura=findViewById(R.id.cb_are_coperta_dura);
        rgFormat = findViewById(R.id.rg_format);
        spnGen = findViewById(R.id.spn_gen);
        rbRating = findViewById(R.id.rb_rating);
        swEsteImprumutata=findViewById(R.id.sw_e_imprumutata);
        btnSalveaza=findViewById(R.id.btn_salveaza);

        //pentru a afisa lista oredefinita de genuri de carti ca un dropdown menu
        ArrayAdapter<String> adapterGenuri = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genuriCarte);
        spnGen.setAdapter(adapterGenuri);

        //pt a vedea la ce nr a ajuns cand am tras cu seekbarul pe ecran
        sbNrPagini.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvNrPagini.setText("Alege nr de pagini mai jos: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nu am nev de nimic aici
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //nu am nev de nimic aici
            }
        });


        btnSalveaza.setOnClickListener(v -> {
            String titlu = etTitlu.getText().toString().trim();
            if(titlu.isEmpty()){
                etTitlu.setError("Introdu titlul cartii!");
                return;
            }

            int nrPagini = sbNrPagini.getProgress();
            boolean areCopertaDura = cbAreCopertaDura.isChecked();

            int selectedId = rgFormat.getCheckedRadioButtonId();
            if(selectedId == -1){
                Toast.makeText(this, "Selecteaza formatul cartii!", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rbSelected = findViewById(selectedId);
            String format = rbSelected.getText().toString();

            String gen = spnGen.getSelectedItem().toString();

            float rating = rbRating.getRating();
            if(rating==0.0f){
                Toast.makeText(this, "Va rugam acordati un rating > 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean eImprumutata = swEsteImprumutata.isChecked();

            Carte carte = new Carte(titlu, nrPagini, areCopertaDura, format, gen, rating, eImprumutata);

            Intent it = new Intent();
            it.putExtra("carte_noua", carte);
            setResult(RESULT_OK, it);
            finish();
        });




    }


}