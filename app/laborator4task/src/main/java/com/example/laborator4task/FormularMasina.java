package com.example.laborator4task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class FormularMasina extends AppCompatActivity {

    private EditText etMarca;
    private EditText etAn;
    private EditText etPret;
    private CheckBox cbEsteElectrica;

    private Spinner spnCaroserie;
    private RadioGroup grCombustibil;
    private Switch swAreGarantie;

    private SeekBar sbPutere;
    private TextView tvPutere;
    private int putereSelectata = 100; //o valoare implicita
    private RatingBar rbRating;

    private DatePicker dpITP;
    private AutoCompleteTextView actvCuloare;
    private final String[] culoriAuto={"Alb", "Albastru", "Argintiu", "Galben", "Gri", "Negru", "Rosu", "Verde", "Maro", "Burgundy", "Portocaliu", "Bleumarin"};


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

        sbPutere = findViewById(R.id.sb_putere);
        tvPutere = findViewById(R.id.tv_label_putere);

        sbPutere.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        putereSelectata=progress;
                        tvPutere.setText("Putere motor: "+putereSelectata+" CP");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nu am treaba
                //se declanseaza cand pun degetul pe slider
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //nu am treaba
                //se declanseaza cand iau degetul dp slider
            }
        });


        dpITP = findViewById(R.id.dp_itp);


        //leg AutoCompleteTextView + setez ArrayAdapter-ul
        actvCuloare=findViewById(R.id.actv_culoare);
        ArrayAdapter<String> adapterCulori = new ArrayAdapter<>(this,
                                                                android.R.layout.simple_dropdown_item_1line,
                                                                culoriAuto);

        actvCuloare.setAdapter(adapterCulori);


        Button btnReset = findViewById(R.id.btn_reset_formular);
        btnReset.setOnClickListener(
                v->{
                    actvCuloare.setText("");
                    etMarca.setText("");
                    etAn.setText("");
                    etPret.setText("");

                    swAreGarantie.setChecked(false);
                    cbEsteElectrica.setChecked(false);

                    rbRating.setRating(0);
                    sbPutere.setProgress(100);

                    //stergem msj de eroare dc existau
                    actvCuloare.setError(null);

                    Toast.makeText(FormularMasina.this, "Formularul a fost curatat cu succes!", Toast.LENGTH_SHORT).show();
                }
        );
    }

    public void salveazaMasina(View view) {
        //extrag valorile introduse de user in acele campuri
        String marca = etMarca.getText().toString().trim();
        String anDarString = etAn.getText().toString().trim();
        String pretDarString = etPret.getText().toString().trim();


        //extrag modelul selectat din Spinner
        String model = spnCaroserie.getSelectedItem().toString();

        //citesc din RadioGroup - aflu care buton e bifat si pe ala il salvez
        int idRadioButtonSelectat = grCombustibil.getCheckedRadioButtonId();
        if (idRadioButtonSelectat == -1) {
            Toast.makeText(this, "Selectati tipul de combustibil!", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbSelectat = findViewById(idRadioButtonSelectat);
        String tipCombustibil = rbSelectat.getText().toString();

        //citesc din CheckBox, Switch si RatingBar
        Boolean esteElectrica = cbEsteElectrica.isChecked();
        boolean areGarantie = swAreGarantie.isChecked();
        float rating = rbRating.getRating();


        // din SeekBar (slider) deja am citit caii putere, in onCreate
        //doar ii introduc direct in obiect aici: putereSelectata


        //extrag data expirarii ITPului din DatePicker
        int ziITP = dpITP.getDayOfMonth();
        int lunaITP = dpITP.getMonth()+1;//lunile incep de la 0
        int anITP = dpITP.getYear();
        String dataITP = String.format("%02d/%02d/%d", ziITP,lunaITP, anITP);

        //extrag culoarea
        String culoare = actvCuloare.getText().toString().trim();


        //validari

        //marca
        if (marca.isEmpty()) {
            etMarca.setError("Introduceti marca masinii!");
            etMarca.requestFocus();
            return;
        }

        //an
        if(anDarString.isEmpty()){
            etAn.setError("Introduceti anul de fabricatie!");
            etAn.requestFocus();
            return;
        }
        int an;
        try{
            an = Integer.parseInt(anDarString);
            int anCurent = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if(an < 1900 || an > anCurent){
                etAn.setError("Anul trebuie sa fie intre 1900 si " + anCurent + "!");
                etAn.requestFocus();
                return;
            }
        }catch(NumberFormatException ex){
            etAn.setError("Anul introdus e invalid!");
            etAn.requestFocus();
            return;
        }

        //pret
        if (pretDarString.isEmpty()) {
            etPret.setError("Introduceti pretul!");
            etPret.requestFocus();
            return;
        }
        double pret = 0.0;
        try {
            pret = Double.parseDouble(pretDarString);
            if (pret <= 0) {
                etPret.setError("Pretul trebuie sa fie un nr pozitiv!");
                etPret.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            etPret.setError("Pretul introdus nu este valid!");
            etPret.requestFocus();
            return;
        }

        //culoare
        if (culoare.isEmpty()) {
            actvCuloare.setError("Introduceti o culoare!");
            actvCuloare.requestFocus();
            return;
        }


        //construiesc obiectul - instantiez cls MasinaAuto
        MasinaAuto masinaNoua = new MasinaAuto(
                model,
                pret,
                areGarantie,
                tipCombustibil,
                marca,
                rating,
                esteElectrica,
                putereSelectata,
                dataITP,
                culoare, an
        );



        //trimit obiectul inapoi in prima fereasta
        Intent it = new Intent();
        Bundle bundle = new Bundle();

        bundle.putSerializable("masinaAdaugata", masinaNoua);
        it.putExtras(bundle);

        setResult(RESULT_OK, it);
        finish();//inchide activitatea curenta si se intoarce la main


    }
}