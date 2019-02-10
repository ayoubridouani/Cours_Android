package com.example.anonsurf.multiactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Client extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_client);
        String msg = getIntent().getStringExtra("keyName");
        //TextView textview = findViewById(R.id.show_msg);
        TextView textview = new TextView(this);
        textview.setText(msg);
        setContentView(textview);

    }



}
