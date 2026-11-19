package com.example.recapitulare3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.listitem.ListItemViewHolder;

import java.util.ArrayList;

public class ListaCeasuriStandard extends AppCompatActivity {
    private ListView lvCeasuriStandard;
    private ArrayList<Smartwatch> ceasuriPrimite;
    private ArrayAdapter<Smartwatch> adapterStd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_ceasuri_standard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvCeasuriStandard = findViewById(R.id.lv_ceasuriStandard);

        ceasuriPrimite = getIntent().getParcelableArrayListExtra("lista_ceasuri");
        if(ceasuriPrimite==null){
            ceasuriPrimite=new ArrayList<>();
        }

        adapterStd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,ceasuriPrimite );
        lvCeasuriStandard.setAdapter(adapterStd);
    }
}