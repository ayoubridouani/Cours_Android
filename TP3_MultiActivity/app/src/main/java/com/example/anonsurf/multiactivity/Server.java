package com.example.anonsurf.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Server extends Activity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        text = findViewById(R.id.msg);
    }

    public void send(View view){
        String msg = text.getText().toString();
        Intent intent = new Intent(this,Client.class);
        intent.putExtra("keyName",msg);
        startActivity(intent);
    }




}
