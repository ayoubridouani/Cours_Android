package com.example.dell.intent_ser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView tx1,tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tx1 = findViewById(R.id.nom);
        tx2 = findViewById(R.id.prix);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();
        Produit monProd = (Produit) b.getSerializable("key");

        tx1.setText("product name is : " + monProd.getName());
        tx2.setText("product price is : " + monProd.getPrix() + " DH");
    }
}
