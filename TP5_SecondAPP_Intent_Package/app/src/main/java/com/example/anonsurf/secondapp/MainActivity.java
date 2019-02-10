package com.example.anonsurf.secondapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
bm=findViewById(R.id.hi);
        /*while(getIntent() == null) {
            Intent i = getIntent();
            Bundle b = i.getExtras();*/
            bm.setText(getApplicationContext().getPackageName());
        //}
    }
}
