package com.example.laborator4task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView tvrezultat;//ca sa pot schimba textul "nicio masina adaugata inca" atunci cand
                        //revin din formular, cu detaliile introduse despre masina


    private ArrayList<MasinaAuto> listaMasini = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //leg xml de java
        tvrezultat=findViewById(R.id.tv_rezultat_masina);


        //buton de vazut lista in fereastra noua cu list view
        Button butonListView = findViewById(R.id.btn_vezi_lista);
        butonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaMasini.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lista este goala! Adauga mai intai o masina.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent it2=new Intent(getApplicationContext(), AfisareArrayListMasini.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cheieListaMasini", listaMasini);
                it2.putExtras(bundle);

                startActivity(it2);
            }
        });

        Button btnDeschideFormular = findViewById(R.id.btn_deschide_formular);
        btnDeschideFormular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), FormularMasina.class);
                startActivityForResult(it, 55);
            }
        });
    }

//    public void deschideFormular(View view) {
//        //codul de aici se executa cand apas butonul deschideFormular, din activity_main.xml
//        Intent it = new Intent(this, FormularMasina.class);
//        startActivityForResult(it, 55);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==55 && resultCode==RESULT_OK && data!=null){

            Bundle bundle = data.getExtras();
            if(bundle!=null){
                MasinaAuto masina = (MasinaAuto) bundle.getSerializable("masinaAdaugata");
                if(masina!=null){
                    listaMasini.add(masina);//salvez in arrayList

                    //dc TextViewul are msj inițial, il curat b4 prima adăugare
                    if (tvrezultat.getText().toString().contains("Nu ai adaugat")) {
                        tvrezultat.setText("");
                    }
                    tvrezultat.append(masina.toString()+"\n-------------------\n");

                }
            }
        }
    }

//    public void deschideListaMasini(View view) {
//
//        if(listaMasini.isEmpty()){
//            Toast.makeText(this, "Lista de masini e goala, adauga macar o masina!", Toast.LENGTH_LONG);
//            return;
//        }
//
//        //trimit arraylistul prin intent catre fereastra noua care il va afisa
//        Intent it2 = new Intent(this, AfisareArrayListMasini.class);
//        Bundle bundle2 = new Bundle();
//        bundle2.putSerializable("cheieListaMasini", listaMasini);
//        it2.putExtras(bundle2);
//        startActivity(it2);
//    }
}