package com.example.org.TP13_personalize_RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RAdapter extends RecyclerView.Adapter<RAdapter.ProduitViewHolder> {
    private ArrayList<Produit> arl;
    private Context context;
    private Bitmap bitmap;
    private static final int PICK_IMAGE_REQUEST = 1;

    public RAdapter(ArrayList<Produit> arl, Context context){
        this.arl=arl;
        this.context=context;
    }

    @NonNull
    @Override
    public ProduitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = ((Activity)parent.getContext()).getLayoutInflater();
        View layout = inflater.inflate(R.layout.produit_layout,parent,false);
        ProduitViewHolder produitViewHolder = new ProduitViewHolder(layout,context,arl);
        return produitViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitViewHolder holder, int position) {

        Produit produit = arl.get(position);
        holder.ref.setText(produit.getRef());
        holder.des.setText(produit.getDes());
        holder.position = position;

        bitmap = produit.getBitmap();
        holder.imgv.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return arl.size();
    }

    public static class ProduitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgv;
        TextView ref,des;
        Context context;
        ArrayList<Produit> arl;
        int position;

        public ProduitViewHolder(@NonNull View itemView, Context context, ArrayList<Produit> arl) {
            super(itemView);
            imgv = itemView.findViewById(R.id.imageView);
            ref = itemView.findViewById(R.id.reftv);
            des = itemView.findViewById(R.id.destv);
            this.arl=arl;
            this.context=context;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v==itemView){

                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity)context).startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        }
    }
}
