package com.example.recapitulare2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaInregistrari extends AppCompatActivity {

   private ArrayList<Excursie> listaExcursii=new ArrayList<>();
   private ArrayAdapter<Excursie> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_inregistrari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAdauga = findViewById(R.id.btn_adauga);
        btnAdauga.setOnClickListener(v -> {
            Intent intent1 = new Intent(ListaInregistrari.this, AdaugaInregistrare.class);
            startActivityForResult(intent1, 67);
        });
        //inchide fereastra/activitatea curenta si IESE DIN APP - ex3
        Button btnIesire = findViewById(R.id.btn_iesire);
        btnIesire.setOnClickListener(v -> {
            finish();
        });


        adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_list_item_1,
                                listaExcursii);
        ListView lvExcursii = findViewById(R.id.lv_lista_inregistrari);
        lvExcursii.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==67 && resultCode==RESULT_OK && data!=null){
//            Carte carte = (Carte) data.getParcelableExtra("carte_noua");
//            if(carte!=null){
//                tvRezultat.setText(carte.toString());
//            }
            Excursie excursie = (Excursie) data.getParcelableExtra("excursie_noua");
            if(excursie!=null){
                listaExcursii.add(excursie);
                adapter.notifyDataSetChanged();
            }
        }

    }
}