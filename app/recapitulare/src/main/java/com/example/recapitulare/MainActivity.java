package com.example.recapitulare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnDeschideFormular;
    private TextView tvRezultat;


    //Obiectul creat va fi salvat intr o colectie gestionata la nivelul activitatii principale.
    private ArrayList<Carte> listaCarti = new ArrayList<>();

    //1.Obiectele salvate in colectia gestionata de activ principala vor fi afisate in controlul ListView cu un adapter standard.
    private ListView lvMain;
    private ArrayAdapter<Carte> adapterStdMain;
    private Button btnVeziListaCustom;


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

        btnDeschideFormular=findViewById(R.id.btn_deschide_formular);

        //metoda startActivityForResult se apeleza la APASAREA BUTONULUI btnDeschideFormular
        //ptc am pus codul in interiorul LISTENERULUI DE CLICK PE BUTONUL btnDeschideFormular!!
        btnDeschideFormular.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormularCarte.class);
            startActivityForResult(intent, 101);
        });
        tvRezultat = findViewById(R.id.tv_rezultat_carte);


        //leg controlul de listview
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //preiau datele din formularul de adaugat o carte
        if(requestCode==101 && resultCode==RESULT_OK && data!=null){
            Carte carte = (Carte) data.getParcelableExtra("carte_noua");
            if(carte!=null){
                tvRezultat.setText(carte.toString());
            }
        }
    }
}