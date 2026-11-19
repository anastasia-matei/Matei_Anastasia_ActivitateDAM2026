package com.example.recapitulare4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btndeschide;
    private Button btncustom;
    private Button btnstd;
    private ArrayList<Pacient> listaPacienti = new ArrayList<>();

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

        btndeschide=findViewById(R.id.btn_deschideFormular);
        btndeschide.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, FormularPacient.class);
            startActivityForResult(intent, 101);
        });
        btncustom=findViewById(R.id.btn_deschideLvCustom);
        btncustom.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaPacientiCustom.class);
            intent.putParcelableArrayListExtra("lista_pacienti", listaPacienti);
            startActivity(intent);
        });

        btnstd=findViewById(R.id.btn_deschideLvStandard);
        btnstd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaPacientiStandard.class);
            intent.putParcelableArrayListExtra("lista_pacienti", listaPacienti);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK && data!=null){
            //preiau datele din intent si le pun in arraylistul de pacienti
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                Pacient pacientPrimit = (Pacient) bundle.getParcelable("pacient_nou");
                if(pacientPrimit!=null){
                    listaPacienti.add(pacientPrimit);
                    Toast.makeText(this, "S a adaugat pacientul cu codul: "+pacientPrimit.getCodPacient(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}