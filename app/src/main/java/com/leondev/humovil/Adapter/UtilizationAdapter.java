package com.leondev.humovil.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leondev.humovil.Model.ModelUtilization;
import com.leondev.humovil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leondev on 31/07/17.
 */

public class UtilizationAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ModelUtilization> datos;

    public UtilizationAdapter(Context context, ArrayList datos) {
        super(context, R.layout.modelo_utilization, datos);
        // Guardamos los parámetros en variables de clase.
        this.context = context;
        this.datos = datos;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View item = inflater.inflate(R.layout.modelo_utilization, null);

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView);
        imagen.setImageResource(datos.get(position).getDrawImg());

        TextView Meta = (TextView) item.findViewById(R.id.txt1);
        Meta.setText("0.0");

        TextView Cueros = (TextView) item.findViewById(R.id.txt2);
        Cueros.setText("" +datos.get(position).getCueros());

        TextView HUvar = (TextView) item.findViewById(R.id.txt3);
        HUvar.setText("" +datos.get(position).getHUtilizationVar());


        return item;
    }


}
