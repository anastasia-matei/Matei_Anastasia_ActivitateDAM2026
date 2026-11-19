package com.example.recapitulare4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PacientAdapter  extends BaseAdapter{

    private Context context;
    private ArrayList<Pacient> listaPacienti;

    public PacientAdapter(Context context, ArrayList<Pacient> listaPacienti) {
        this.context = context;
        this.listaPacienti = listaPacienti;
    }


    @Override
    public int getCount() {
        return listaPacienti.size();
    }
    @Override
    public Object getItem(int position) {
        return listaPacienti.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent,false);

        //tv_cod_eurgenta  tv_durere_grupasang tv_sex_greutate
        TextView tv_cod_eurgenta = view.findViewById(R.id.tv_cod_eurgenta);
        TextView tv_durere_grupasang = view.findViewById(R.id.tv_durere_grupasang);
        TextView tv_sex_greutate = view.findViewById(R.id.tv_sex_greutate);

        Pacient pacient = (Pacient) getItem(position);
        if(pacient!=null){
            tv_cod_eurgenta.setText(context.getString(R.string.id)+pacient.getCodPacient()+"\n"+context.getString(R.string.urgenta)+(pacient.iseUrgenta()?context.getString(R.string.da):context.getString(R.string.nu)));

            tv_durere_grupasang.setText(context.getString(R.string.nivel)+pacient.getNivelDurere()+"\n"+context.getString(R.string.grupa)+" "+pacient.getGrupaSanguina());

            tv_sex_greutate.setText(context.getString(R.string.sex)+pacient.getSex()+context.getString(R.string.greutate)+pacient.getGreutate()+context.getString(R.string.kg));
        }



        return view;
    }
}
