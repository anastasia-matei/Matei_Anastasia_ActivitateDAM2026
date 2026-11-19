package com.example.recapitulare4;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormularPacient extends AppCompatActivity {
    private EditText etcodpacient;
    private RadioGroup rgSex;
    private TextView tvGreutate;
    private SeekBar sbGreutate;
    private CheckBox ckbEurgenta;
    private Spinner spnGrupasanguina;
    private RatingBar rbarDurere;
    private Button btnsalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formular_pacient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etcodpacient=findViewById(R.id.et_codpacient);
        rgSex = findViewById(R.id.rg_sex);
        tvGreutate=findViewById(R.id.tv_greutate);
        sbGreutate=findViewById(R.id.sb_greutate);
        ckbEurgenta=findViewById(R.id.ckb_eurgenta);
        spnGrupasanguina=findViewById(R.id.spn_grupasanguina);
        rbarDurere=findViewById(R.id.rbar_durere);
        btnsalveaza=findViewById(R.id.btn_salvarepacient);


        sbGreutate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvGreutate.setText("Greutatea dvs: "+progress+" kg");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnsalveaza.setOnClickListener(v -> {
            String codtext = etcodpacient.getText().toString().trim();
            if(codtext.isEmpty())
            {
                etcodpacient.setError("Introdu cod pacient");
                return;
            }
            int cod = Integer.parseInt(codtext);

            int idselectat = rgSex.getCheckedRadioButtonId();
            if(idselectat==-1){
                Toast.makeText(this, "selecteaza sexul", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rbsex = findViewById(idselectat);
            String sex = rbsex.getText().toString().trim();

            int greutate = sbGreutate.getProgress();

            boolean eurgenta = ckbEurgenta.isChecked();

            String grupasanguina = spnGrupasanguina.getSelectedItem().toString().trim();

            float niveldurere = rbarDurere.getRating();

            Pacient pacient = new Pacient(cod, sex, greutate, eurgenta, grupasanguina, niveldurere);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable("pacient_nou", pacient);
            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}