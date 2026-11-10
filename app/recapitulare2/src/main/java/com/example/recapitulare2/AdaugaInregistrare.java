package com.example.recapitulare2;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
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

public class AdaugaInregistrare extends AppCompatActivity {
    private EditText etDestinatie;
    private TextView tvPret;
    private SeekBar sbPret;
    private CheckBox cbBagajExtra;
    private Switch swPensionar;
    private RadioGroup rgAnotimp;
    private RatingBar rbarRating;
    private Spinner spnLocatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_inregistrare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDestinatie=findViewById(R.id.et_destinatie);
        tvPret=findViewById(R.id.tv_pret);
        sbPret=findViewById(R.id.sb_pret);
        cbBagajExtra=findViewById(R.id.cb_bagaj_extra);
        swPensionar=findViewById(R.id.sw_pensionar);
        rgAnotimp=findViewById(R.id.rg_anotimp);
        rbarRating=findViewById(R.id.rbar_rating);
        spnLocatie=findViewById(R.id.spn_locatii);

        Button btnSave = findViewById(R.id.btn_salveaza_excursie);
        btnSave.setOnClickListener(v -> {

            String destinatie = etDestinatie.toString().trim();
            if(destinatie.isEmpty()){
                etDestinatie.setError("Scrie boss destinatia!");
                return;
            }

            int pret = sbPret.getProgress();
            tvPret.setText("Pretul per persoana este: "+pret);


            boolean areBagajExtra;
            if(cbBagajExtra.isChecked()){
                areBagajExtra=true;
            }else {
                areBagajExtra=false;
            }

            boolean ePensionar=swPensionar.isChecked();

            //radiogroup in ce anotimp e
            int idSelectat = rgAnotimp.getCheckedRadioButtonId();
            if(idSelectat==-1){
                Toast.makeText(this, "Te rog sa alegi anotimpul excursiei", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rbSelectat = findViewById(idSelectat);
            String anotimp = rbSelectat.getText().toString();


            float rating = rbarRating.getRating();
            if(rating==0.0f){
                Toast.makeText(this,"Alege un rating > 0", Toast.LENGTH_SHORT).show();
                return;
            }

            String locatie = spnLocatie.getSelectedItem().toString();

            Excursie excursie = new Excursie(destinatie, pret, areBagajExtra, ePensionar, anotimp, rating, locatie);
            Intent intent = new Intent();
            intent.putExtra("excursie_noua", excursie);
            setResult(RESULT_OK, intent);
            finish();
        });
    }


}