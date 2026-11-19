package com.example.recapitulare4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaPacientiCustom extends AppCompatActivity {
    private ListView lvCustom;
    private ArrayList<Pacient>listaPacientiCustom =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pacienti_custom);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvCustom=findViewById(R.id.lv_custom);
        Intent intent = getIntent();
        listaPacientiCustom=intent.getParcelableArrayListExtra("lista_pacienti");
        if(listaPacientiCustom == null){
            listaPacientiCustom=new ArrayList<>();
        }
        PacientAdapter adapterCustom = new PacientAdapter(ListaPacientiCustom.this, listaPacientiCustom);
        lvCustom.setAdapter(adapterCustom);


        //click afiseaza pacientul in toast
        lvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pacient pacientselectat = listaPacientiCustom.get(position);
                String mesaj = pacientselectat.toString();
                Toast.makeText(ListaPacientiCustom.this, mesaj, Toast.LENGTH_LONG).show();
            }
        });


        //click lung ii face urgenta in cealalta valoare
        lvCustom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Pacient pacientselectat = listaPacientiCustom.get(position);
                pacientselectat.seteUrgenta(!pacientselectat.iseUrgenta());

                adapterCustom.notifyDataSetChanged();

                Toast.makeText(ListaPacientiCustom.this, "S a inversat stare urgenta", Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }


}