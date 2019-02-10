package com.example.anonsurf.tp14commandeproduit.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.anonsurf.tp14commandeproduit.AdapterProduct;
import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.Product;
import com.example.anonsurf.tp14commandeproduit.R;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity{
    private ListView listV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        listV = this.findViewById(R.id.listv);

        ArrayList<Product> data = Controller.getAllProducts();

        AdapterProduct adapaterProduct = new AdapterProduct(this,R.layout.custom_listview_admin,data);

        listV.setAdapter(adapaterProduct);
    }
}
