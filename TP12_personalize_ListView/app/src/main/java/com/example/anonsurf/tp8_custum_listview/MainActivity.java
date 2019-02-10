package com.example.anonsurf.tp8_custum_listview;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText text1,text2;
    private Button btn1,btn2;
    private ListView list;

    private ArrayList<Produit> produits;
    private AdapterProduit adapter;
    private int position_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.ref);
        text2 = findViewById(R.id.des);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        list = findViewById(R.id.list);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        produits = new ArrayList<>();
        adapter = new AdapterProduit(this,R.layout.design_list,produits);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position_clicked = position;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 48);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custum_dialog);
                dialog.setTitle("Information sur le produit");
                TextView text1 = dialog.findViewById(R.id.desss);
                text1.setText(produits.get(position).getDestinataire());
                TextView text2 = dialog.findViewById(R.id.refff);
                text2.setText(produits.get(position).getReference());
                ImageView image = dialog.findViewById(R.id.imgg);
                image.setImageBitmap(produits.get(position).getImage());
                dialog.show();
                return false;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 48 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                produits.get(position_clicked).setImage(bitmap);
                adapter.notifyDataSetChanged();
            } catch (IOException e) {}
        }
    }

    @Override
    public void onClick(View v){
        Bitmap default_image = BitmapFactory.decodeResource(getResources(),R.drawable.default_image);
        if(v.getId() == R.id.btn1 && !text1.getText().toString().equals("") && !text2.getText().toString().equals("")){
            produits.add(new Produit(text1.getText().toString(),text2.getText().toString(),default_image));
            adapter.notifyDataSetChanged();
            text1.setText("");
            text2.setText("");
        }
        if(v.getId() == R.id.btn2 && produits.size() > 0){
            produits.remove(produits.size()-1);
            adapter.notifyDataSetChanged();
        }
        if(produits.size() == 0){
            btn2.setEnabled(false);
        }else{
            btn2.setEnabled(true);
        }
    }
}
