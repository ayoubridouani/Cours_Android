package com.example.anonsurf.tp14commandeproduit.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;
import com.example.anonsurf.tp14commandeproduit.AdapterProduct;
import com.example.anonsurf.tp14commandeproduit.Product;

import java.util.ArrayList;

public class BasketOfProducts extends AppCompatActivity {
    private ListView listV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_of_products);

        listV = this.findViewById(R.id.listp);

        ArrayList<Product> data = Controller.listeForPanier();

        AdapterProduct adapaterProduct = new AdapterProduct(this,R.layout.custom_listview_admin,data);

        listV.setAdapter(adapaterProduct);
    }
}
