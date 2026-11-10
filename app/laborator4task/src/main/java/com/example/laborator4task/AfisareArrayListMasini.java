package com.example.laborator4task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AfisareArrayListMasini extends AppCompatActivity {

    private ListView lvMasini;
    private ArrayList<MasinaAuto> listaMasini;

    //pt seminar 6 - adapter personalizat:
    private int idModificat = -1;
    private MasinaAdapter adapterPersonalizat = null;
    private static final int REQUEST_CODE_EDITARE = 101;

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
            //listaMasini=(ArrayList<MasinaAuto>) bundle.getSerializable("cheieListaMasini");
            listaMasini = bundle.getParcelableArrayList("cheieListaMasini");
        }
        if(listaMasini==null){
            listaMasini=new ArrayList<>();
        }

        //array adapter apeleaza toString pe fiecare masina din lista ca sa o afis cum am vrut eu
//        ArrayAdapter<MasinaAuto> adapter = new ArrayAdapter<>(
//                                        getApplicationContext(),
//                                        android.R.layout.simple_list_item_1,
//                                        listaMasini
//                                );
//
//        lvMasini.setAdapter(adapter);
        //am comentat mai sus ca sa pot implementa custom adapterul in seminar 6
        adapterPersonalizat = new MasinaAdapter(listaMasini, getApplicationContext(), R.layout.item_masina);
        lvMasini.setAdapter(adapterPersonalizat);


        lvMasini.setOnItemClickListener((parent, view, position, id) -> {
            idModificat = position; // Retinem pozitia masinii selectate pt editare

            Intent intentModifica = new Intent(getApplicationContext(), FormularMasina.class);
            // Trimitem obiectul selectat prin Parcelable
            intentModifica.putExtra("masina_editata", listaMasini.get(position));

            startActivityForResult(intentModifica, REQUEST_CODE_EDITARE);
        });


        //cerinta 3 - lab5
        //click scurt pe un element din ListView => afisez elem in Toast
//        lvMasini.setOnItemClickListener((parent, view, position, id) -> {
//            // 1. Extragem obiectul apăsat din listă după poziția sa (index)
//            MasinaAuto masinaSelectata = listaMasini.get(position);
//
//            // 2. Afișăm detaliile prin Toast (apelează tot toString())
//            Toast.makeText(AfisareArrayListMasini.this, masinaSelectata.toString(), Toast.LENGTH_LONG).show();
//        });



        //cerinta4 - la long click
        lvMasini.setOnItemLongClickListener((parent,view,position, id)->{
            MasinaAuto masinaStearsa = listaMasini.remove(position);
            adapterPersonalizat.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Am sters: " + masinaStearsa.getMarca(), Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_EDITARE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();

            if (bundle != null) {
                MasinaAuto masinaModificata = (MasinaAuto) bundle.getParcelable("masinaAdaugata");

                if (masinaModificata != null && idModificat != -1) {
                    // inlocuiesc  obiectul vechi cu ala modificat
                    listaMasini.set(idModificat, masinaModificata);

                    //notific adapterul ca s-a schimbat sursa de date
                    //mi se actualizeaza ce apare pe ecran, lista cu noile valori ale masinii, dupa editare
                    adapterPersonalizat.notifyDataSetChanged();

                    Toast.makeText(this, "Masina a fost modificata cu succes!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}