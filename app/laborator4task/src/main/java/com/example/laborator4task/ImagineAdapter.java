package com.example.laborator4task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private int resursaLayout;
    private List<ImaginiDomeniu> lista;

    public ImagineAdapter(Context ctx, int resursaLayout, List<ImaginiDomeniu> lista) {
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista != null ? lista.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(resursaLayout,parent, false);


        ImageView ivImagine = view.findViewById(R.id.iv_imagine);
        TextView tvImagine = view.findViewById(R.id.tv_imagine);

        ImaginiDomeniu imgCurenta = (ImaginiDomeniu) getItem(position);
        if(imgCurenta!=null){
            ivImagine.setImageBitmap(imgCurenta.getImagine());
            tvImagine.setText(imgCurenta.getTextAfisat());
        }
        return view;
    }
}
