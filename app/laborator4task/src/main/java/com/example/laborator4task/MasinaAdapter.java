package com.example.laborator4task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MasinaAdapter extends BaseAdapter {
    private List<MasinaAuto> masini = null;
    private Context ctx;
    private int resursaLayout;

    public MasinaAdapter(List<MasinaAuto> masini, Context ctx, int resursaLayout) {
        this.masini = masini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        if (masini == null) {
            return 0;
        }
        return masini.size();
    }

    @Override
    public Object getItem(int position) {
        return masini.get(position);
    }
    //sau mai detailed:
//    if (masini != null && position >= 0 && position < masini.size()) {
//        return masini.get(position);
//    }
//        return null;

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(resursaLayout, parent, false);

        TextView tvMarcaModel = view.findViewById(R.id.tv_item_marca_model);
        TextView tvRating = view.findViewById(R.id.tv_item_rating);
        TextView tvPretAnPutere = view.findViewById(R.id.tv_item_pret_an_putere);
        TextView tvCombustibilElectrica = view.findViewById(R.id.tv_item_combustibil_electrica);
        TextView tvGarantie = view.findViewById(R.id.tv_item_garantie);
        TextView tvCuloare = view.findViewById(R.id.tv_item_culoare);
        TextView tvItp = view.findViewById(R.id.tv_item_itp);

        MasinaAuto masina = (MasinaAuto) getItem(position);


        if(masina!=null){
            //pe primul rand
            tvMarcaModel.setText("Marca: "+masina.getMarca()+" | Model: "+ masina.getModel());
            tvRating.setText("Rating: "+masina.getRating()+"★");

            //pe randul 2
            tvPretAnPutere.setText("Pret: "+masina.getPret()+"euro | An fabricatie: "+masina.getAn()+" | Putere "+masina.getPutere()+"CP");

            //pe randul 3

            String eElectricaText = masina.isEsteElectrica()? "Da" : "Nu";
            tvCombustibilElectrica.setText(masina.getTipCombustibil()+" Electrica: "+eElectricaText);

            String garantieText = masina.isAreGarantie()?"Da":"Nu";
            tvGarantie.setText("Garantie valabila: "+garantieText);

            //rand 4
            tvCuloare.setText("Culoare: "+masina.getCuloare());
            tvItp.setText("Data expirare ITP: "+masina.getDataITP());
        }
        return view;
    }
}
