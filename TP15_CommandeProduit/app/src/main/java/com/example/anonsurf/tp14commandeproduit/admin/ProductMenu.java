package com.example.anonsurf.tp14commandeproduit.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;

public class ProductMenu extends AppCompatActivity {
    private Button add;
    private Button list;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_menu);

        controller = new Controller(this);

        add = this.findViewById(R.id.add);
        list = this.findViewById(R.id.list);

        add.setOnClickListener(controller);
        list.setOnClickListener(controller);
    }
}