package com.example.recapitulare3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CeasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Smartwatch> listaCeasuri;


    public CeasAdapter(Context context, ArrayList<Smartwatch> listaCeasuri) {
        this.context = context;
        this.listaCeasuri = listaCeasuri;
    }

    @Override
    public int getCount() {
        return listaCeasuri.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCeasuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R. layout.item_ceas, parent, false);


        TextView tvId = view.findViewById(R.id.tv_IdCeas);
        TextView tvProducator = view.findViewById(R.id.tv_producator);
        TextView tvCapacitateNFC = view.findViewById(R.id.tv_capacitate_si_NFC);
        TextView tvMaterialRating = view.findViewById(R.id.tv_materialCurea_si_rating);

        Smartwatch ceas = (Smartwatch) getItem(position);
        if(ceas!=null){
            tvId.setText("ID: "+ceas.getIdCeas());
            tvProducator.setText("Producator: "+ceas.getProducator());

            tvCapacitateNFC.setText("Capacitate baterie: "+ceas.getCapacitateBaterie()+" | Are NFC? "+(ceas.isAreNFC()?"DA":"NU"));
            tvMaterialRating.setText("Material curea: "+ceas.getMaterialCurea()+" | Rating: "+ceas.getRating()+" /5 ★" );
        }
        return view;
    }
}
