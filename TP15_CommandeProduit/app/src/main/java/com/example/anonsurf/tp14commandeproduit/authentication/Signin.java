package com.example.anonsurf.tp14commandeproduit.authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;

public class Signin extends AppCompatActivity {
    private EditText firstname;
    private EditText lastname;
    private EditText username;
    private EditText password;
    private Button register;
    private Spinner spinner;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        controller = new Controller(this);

        firstname = this.findViewById(R.id.firstname);
        lastname = this.findViewById(R.id.lastname);
        username = this.findViewById(R.id.username);
        password = this.findViewById(R.id.password);
        register = this.findViewById(R.id.register);
        spinner = this.findViewById(R.id.groupID);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.groupID,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        register.setOnClickListener(controller);
    }

    public EditText getFirstname() {
        return firstname;
    }

    public EditText getLastname() {
        return lastname;
    }

    public EditText getUsername() {
        return username;
    }

    public EditText getPassword() {
        return password;
    }

    public Button getRegister() {
        return register;
    }

    public Spinner getSpinner() {
        return spinner;
    }
}
