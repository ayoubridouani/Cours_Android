package com.example.anonsurf.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.click);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent = intent.setClassName("com.example.anonsurf.secondapp","com.example.anonsurf.secondapp.MainActivity");
                //Bundle b = new Bundle();
                /*b.putString("test","hello world");
                intent.putExtras(b);*/
                startActivity(intent);
            }
        });
    }
}
