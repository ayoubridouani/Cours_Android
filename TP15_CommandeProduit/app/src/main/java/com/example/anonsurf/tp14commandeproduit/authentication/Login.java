package com.example.anonsurf.tp14commandeproduit.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;

public class Login extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private Button login;
    private TextView signin;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controller = new Controller(this,this.getApplicationContext());

        user = this.findViewById(R.id.user);
        pass = this.findViewById(R.id.pass);
        login = this.findViewById(R.id.login);
        signin = this.findViewById(R.id.signin);

        login.setOnClickListener(controller);
        signin.setOnClickListener(controller);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        controller.onActivityResult(requestCode, resultCode, data);
    }

    public EditText getUser() {
        return user;
    }

    public EditText getPass() {
        return pass;
    }
}
