package com.example.recapitulare3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaCeas extends AppCompatActivity {

    private Button btnSalvare;
    private EditText etIdCeas;
    private RadioGroup rgProducator;
    private SeekBar sbCapacitateBaterie;
    private CheckBox ckbAreNFC;
    private Spinner spnMaterialCurea;
    private RatingBar rbarRating;
    private TextView tvCapacitateBaterie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_ceas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSalvare=findViewById(R.id.btn_salveazaCeas);
        etIdCeas = findViewById(R.id.et_idCeas);
        rgProducator = findViewById(R.id.rg_producator);
        sbCapacitateBaterie = findViewById(R.id.sb_capacitateBaterie);
        ckbAreNFC = findViewById(R.id.ckb_areNFC);
        spnMaterialCurea = findViewById(R.id.spn_materialCurea);
        rbarRating = findViewById(R.id.rbar_rating);
        tvCapacitateBaterie=findViewById(R.id.tv_capacitateBaterie);

        sbCapacitateBaterie.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCapacitateBaterie.setText("Capacitatea bateriei este: "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnSalvare.setOnClickListener(v -> {
            String idCeasText = etIdCeas.getText().toString().trim();
            if(idCeasText.isEmpty()){
                etIdCeas.setError("Introdu ID ul ceasului!");
                return;
            }
            int idCeas = Integer.parseInt(idCeasText);

            int idSelectat = rgProducator.getCheckedRadioButtonId();
            if(idSelectat==-1){
                Toast.makeText(this, "Selecteaza producatorul!", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rbProducator = findViewById(idSelectat);
            String producator = rbProducator.getText().toString();


            int capacitate = sbCapacitateBaterie.getProgress();
            boolean areNFC = ckbAreNFC.isChecked();

            String materialCurea = spnMaterialCurea.getSelectedItem().toString();

            float rating = rbarRating.getRating();

            Smartwatch ceas = new Smartwatch(idCeas, producator, capacitate, areNFC, materialCurea, rating);

            Intent intentRezultat = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("ceas_nou", ceas);
            intentRezultat.putExtras(bundle);
            setResult(RESULT_OK, intentRezultat);
            finish();
        });
    }
}