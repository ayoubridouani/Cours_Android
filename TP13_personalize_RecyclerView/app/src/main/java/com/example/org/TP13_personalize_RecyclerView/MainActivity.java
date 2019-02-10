package com.example.org.TP13_personalize_RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText ref,des,ch;
    Button add,clear;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Produit> arl;
    RAdapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ref = findViewById(R.id.ref);
        des = findViewById(R.id.des);
        ch = findViewById(R.id.chemin);
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);

        arl = new ArrayList<>();
        rAdapter = new RAdapter(arl,this);
        recyclerView.setAdapter(rAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.add(new Produit(ref.getText().toString(),
                                    des.getText().toString(),
                                    ch.getText().toString()
                ));
                rAdapter.notifyDataSetChanged();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arl.clear();
                rAdapter.notifyDataSetChanged();
            }
        });
    }
}
