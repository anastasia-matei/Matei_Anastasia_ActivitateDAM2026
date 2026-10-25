package com.example.laborator4task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    private TextView tvrezultat;//ca sa pot schimba textul "nicio masina adaugata inca" atunci cand
                        //revin din formular, cu detaliile introduse despre masina

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

        //!!!!!
        tvrezultat=findViewById(R.id.tv_rezultat_masina);
    }

    public void deschideFormular(View view) {
        //codul de aici se executa cand apas butonul deschideFormular, din activity_main.xml
        Intent it = new Intent(this, FormularMasina.class);
        startActivityForResult(it, 55);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==55 && resultCode==RESULT_OK && data!=null){

            Bundle bundle = data.getExtras();
            if(bundle!=null){
                MasinaAuto masina = (MasinaAuto) bundle.getSerializable("masinaAdaugata");
                if(masina!=null){
                    //dc TextViewul are msj inițial, il curat b4 prima adăugare
                    if (tvrezultat.getText().toString().contains("Nu ai adaugat")) {
                        tvrezultat.setText("");
                    }
                    tvrezultat.append(masina.toString()+"\n-------------------\n");
                }
            }
        }
    }
}