package com.example.anonsurf.manifest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent in = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:0925478951"));
        Intent in = new Intent(Intent.ACTION_CALL,Uri.parse("tel:0925478951"));
        startActivity(in);
    }
}
