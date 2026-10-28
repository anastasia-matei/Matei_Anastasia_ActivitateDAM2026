package com.example.laborator4task;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfisareArrayListMasini extends AppCompatActivity {

    private ListView lvMasini;
    private ArrayList<MasinaAuto> listaMasini;

    //pt cerinta 4
    private ArrayAdapter<MasinaAuto> adapterC4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_array_list_masini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //fac legatura cu elementul vizual listview din xml
        lvMasini = findViewById(R.id.lv_masini);


        //extrag bundleul din intent
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            listaMasini=(ArrayList<MasinaAuto>) bundle.getSerializable("cheieListaMasini");
        }
        if(listaMasini==null){
            listaMasini=new ArrayList<>();
        }

        //array adapter apeleaza toString pe fiecare masina din lista ca sa o afis cum am vrut eu
        ArrayAdapter<MasinaAuto> adapter = new ArrayAdapter<>(
                                        getApplicationContext(),
                                        android.R.layout.simple_list_item_1,
                                        listaMasini
                                );

        lvMasini.setAdapter(adapter);



        //cerinta 3 - lab5
        //click scurt pe un element din ListView => afisez elem in Toast
        lvMasini.setOnItemClickListener((parent, view, position, id) -> {
            // 1. Extragem obiectul apăsat din listă după poziția sa (index)
            MasinaAuto masinaSelectata = listaMasini.get(position);

            // 2. Afișăm detaliile prin Toast (apelează tot toString())
            Toast.makeText(AfisareArrayListMasini.this, masinaSelectata.toString(), Toast.LENGTH_LONG).show();
        });



        //cerinta4 - la long click
        lvMasini.setOnItemLongClickListener((parent,view,position, id)->{
            MasinaAuto masinaStearsa = listaMasini.remove(position);
            adapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Am sters: " + masinaStearsa.getMarca(), Toast.LENGTH_SHORT).show();

            return true;
        });
    }
}