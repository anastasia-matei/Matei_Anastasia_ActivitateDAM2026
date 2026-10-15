package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateRezultat extends AppCompatActivity {

    private int nr1;
    private int nr2;//am declarat 2 atrinute private pt cerinta 10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_rezultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //aflam intentul princare acticitaterezultat a fost lansata
        Intent it = getIntent();

        //preiau bundleul din intentul prin care a fost lansata activitatea
        Bundle bundle = it.getExtras();

        //in acest bundle am un string si 2 intenturi
        String mesaj = bundle.getString("mesaj", "");//"" e val implicita
        this.nr1 = bundle.getInt("nr1", 0); //0 e val implicita
        this.nr2 = bundle.getInt("nr2", 0);


        //ex9
        Toast.makeText(this, mesaj, Toast.LENGTH_SHORT).show();

    }

    public void intoarceInActivitate(View view) {
        String altMesaj = "Mesaj nou";
        int suma = this.nr1 + this.nr2;

        //ca sa intoarcem mesajul e nev de un intent
        //nu mai trb sa ii spun ce activitate sa deschida, ptc activ se inchide.
        //trb sa inchidem activ din vf stivei ptc revine cealalta
        //deci iau constr fara param
        //dispare si contextul actual si revine cel vechi, deci nici contextul nu il pun

        Intent it = new Intent();
        it.putExtra("altMesaj", altMesaj);
        it.putExtra("suma", suma);//cheia e "suma" si valoarea e ce am calcuat mai sus aici

        //cand inchidem activitatea ii setam rezultatul:
        //iau semnatura cu 2 chestii, cu Intentul ptc vr sa trimit si acele lucruri
        setResult(RESULT_OK, it);
        finish();//inchidem activitatea. Prin apelul lui finish() se apeleaza onDestroy


    }
}