package com.example.recapitulare3;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdauga;
    private ArrayList<Smartwatch> listaCeasuri = new ArrayList<>();

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

        btnAdauga = findViewById(R.id.btn_adaugaCeas);
        btnAdauga.setOnClickListener(v -> {
            Intent intentDeschidere = new Intent(MainActivity.this, AdaugaCeas.class);
            startActivityForResult(intentDeschidere, 101);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //cand ma intorc din formular cu obiectul creat si il voi adauga in lista
        if(requestCode==101 && resultCode==RESULT_OK && data!=null){
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                Smartwatch ceasPrimit = (Smartwatch) bundle.getParcelable("ceas_nou");
                if(ceasPrimit!=null){
                    listaCeasuri.add(ceasPrimit);
                    Toast.makeText(this, "Ceasul s a adaugat cu succes", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}