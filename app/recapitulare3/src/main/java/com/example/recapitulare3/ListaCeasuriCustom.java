package com.example.recapitulare3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import kotlin.TuplesKt;

public class ListaCeasuriCustom extends AppCompatActivity {

    private ListView lvCeasuri;
    private Button btnrevin;
    private ArrayList<Smartwatch> ceasuriPrimite = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_ceasuri_custom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvCeasuri = findViewById(R.id.lv_ceasuri);
        Intent intent = getIntent();

        ceasuriPrimite = intent.getParcelableArrayListExtra("lista_ceasuri");
        if (ceasuriPrimite == null) {
            ceasuriPrimite = new ArrayList<>();
        }
        CeasAdapter adapter = new CeasAdapter(ListaCeasuriCustom.this, ceasuriPrimite);
        lvCeasuri.setAdapter(adapter);


        //btnrevin ma intoarce in mainactivity
        btnrevin=findViewById(R.id.btn_inapoiInMain);
        btnrevin.setOnClickListener(v -> {
            finish();
        });


        lvCeasuri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Smartwatch ceasSelectat = ceasuriPrimite.get(position);
                String mesaj = getString(R.string.toast_ceas_selectat)+"\n"+ceasSelectat.toString();
                Toast.makeText(ListaCeasuriCustom.this, mesaj, Toast.LENGTH_LONG).show();
            }
        });

        lvCeasuri.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Smartwatch ceasSelectat = ceasuriPrimite.get(position);
                ceasSelectat.setCapacitateBaterie(ceasSelectat.getCapacitateBaterie()-2);
                adapter.notifyDataSetChanged();

                String mesaj = getString(R.string.toast_capacitate_scazuta)+ceasSelectat.getIdCeas();
                Toast.makeText(ListaCeasuriCustom.this, mesaj, Toast.LENGTH_LONG).show();

                return true;
            }
        });


    }
}