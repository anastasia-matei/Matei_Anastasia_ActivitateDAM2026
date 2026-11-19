package com.example.recapitulare4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaPacientiStandard extends AppCompatActivity {
    private ListView lvStandard;
    private ArrayList<Pacient> listaPrimita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pacienti_standard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvStandard=findViewById(R.id.lv_standard);
        Intent intent = getIntent();
        listaPrimita=intent.getParcelableArrayListExtra("lista_pacienti");



        if(listaPrimita==null){
            listaPrimita=new ArrayList<>();
        }

        ArrayAdapter<Pacient> adapterStd = new ArrayAdapter<>(this,
                                                android.R.layout.simple_list_item_1,
                                                listaPrimita);
        lvStandard.setAdapter(adapterStd);
    }
}