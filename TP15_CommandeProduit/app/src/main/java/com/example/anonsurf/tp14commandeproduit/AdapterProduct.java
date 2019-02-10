package com.example.anonsurf.tp14commandeproduit;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Product> {
    private Context context;
    private int resource;
    private ArrayList<Product> product;

    public AdapterProduct(Context context, int resource, ArrayList<Product> product) {
        super(context, resource, product);
        this.context = context;
        this.resource = resource;
        this.product = product;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(resource , parent, false);

        if(resource == R.layout.custom_listview_admin) {
            TextView ref = view.findViewById(R.id.ref_cust);
            TextView des = view.findViewById(R.id.des_cust);
            ImageView image = view.findViewById(R.id.img_cust);

            ref.setText(product.get(position).getReference());
            des.setText(product.get(position).getDesignation());
            image.setImageURI(Uri.parse(product.get(position).getPathImage()));
        }

        if(resource == R.layout.custum_listview_client){
            final ViewHolder vh = new ViewHolder();

            vh.id = view.findViewById(R.id.id_cust);
            vh.ref = view.findViewById(R.id.ref_cust);
            vh.des = view.findViewById(R.id.des_cust);
            vh.image = view.findViewById(R.id.img_cust);
            vh.buyit = view.findViewById(R.id.buyit);
            vh.position = position;

            vh.id.setText(String.valueOf(product.get(vh.position).getId()));
            vh.ref.setText(product.get(vh.position).getReference());
            vh.des.setText(product.get(vh.position).getDesignation());
            vh.image.setImageURI(Uri.parse(product.get(vh.position).getPathImage()));
            vh.buyit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Controller.addToBasket(Controller.getUSERId(),Integer.parseInt(vh.id.getText().toString()));
                    Toast.makeText(context,"this product added to your basket",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    static class ViewHolder {
        private TextView id;
        private TextView ref;
        private TextView des;
        private ImageView image;
        private Button buyit;
        private int position;
    }
}
