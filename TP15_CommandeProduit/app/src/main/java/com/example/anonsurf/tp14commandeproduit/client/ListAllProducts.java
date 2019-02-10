package com.example.anonsurf.tp14commandeproduit.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;
import com.example.anonsurf.tp14commandeproduit.AdapterProduct;
import com.example.anonsurf.tp14commandeproduit.Product;

import java.util.ArrayList;

public class ListAllProducts extends AppCompatActivity {
    private ListView listV;
    private Button buyit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_products);

        listV = this.findViewById(R.id.listc);

        ArrayList<Product> data = Controller.getAllProducts();

        AdapterProduct adapaterProduct = new AdapterProduct(this,R.layout.custum_listview_client,data);

        listV.setAdapter(adapaterProduct);
    }
}
