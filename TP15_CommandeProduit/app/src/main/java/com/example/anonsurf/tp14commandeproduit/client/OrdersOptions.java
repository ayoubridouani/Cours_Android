package com.example.anonsurf.tp14commandeproduit.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;

public class OrdersOptions extends AppCompatActivity {
    private Controller controller;

    private Button list;
    private Button basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_options);

        controller = new Controller(this);

        list = this.findViewById(R.id.allproduct);
        basket = this.findViewById(R.id.basketOfProducts);

        list.setOnClickListener(controller);
        basket.setOnClickListener(controller);
    }
}
