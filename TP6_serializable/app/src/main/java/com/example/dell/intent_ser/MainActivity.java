package com.example.dell.intent_ser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nom,prix;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);
        nom = findViewById(R.id.nom);
        prix = findViewById(R.id.prix);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                String prix1 = prix.getText().toString();
                String nom1 = nom.getText().toString();

                Produit p = new Produit(nom1,prix1);
                Bundle bundle = new Bundle();

                bundle.putSerializable("key",p);
                intent.putExtras(bundle);

                startActivity(intent);
            }

        });

    }
}
