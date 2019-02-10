package com.example.anonsurf.tp8_custum_listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProduit extends ArrayAdapter<Produit> {

    private Context context;
    private int ressource;
    private ArrayList<Produit> produits;

    AdapterProduit(Context context, int ressource, ArrayList<Produit> produits){
        super(context,ressource,produits);
        this.context = context;
        this.ressource = ressource;
        this.produits = produits;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(ressource , parent, false);

        TextView ref = view.findViewById(R.id.reff);
        TextView des = view.findViewById(R.id.dess);
        ImageView image = view.findViewById(R.id.img);

        ref.setText(produits.get(position).getReference());
        des.setText(produits.get(position).getDestinataire());
        image.setImageBitmap(produits.get(position).getImage());

        return view;
    }
}
