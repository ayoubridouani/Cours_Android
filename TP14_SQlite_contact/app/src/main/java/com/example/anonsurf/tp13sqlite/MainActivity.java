package com.example.anonsurf.tp13sqlite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Dialog dialog;
    private Button add,list;
    private Contact contact;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        list = findViewById(R.id.list);
        list.setOnClickListener(this);

        contact = new Contact(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add){
            db = contact.getWritableDatabase();
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.ajouter_contact);
            dialog.setTitle("Ajouter Contact");
            dialog.show();
            Button ajouter = dialog.findViewById(R.id.ajouter);
            ajouter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    EditText name = dialog.findViewById(R.id.name);
                    EditText phone = dialog.findViewById(R.id.phone);
                    EditText email = dialog.findViewById(R.id.email);
                    int error = 0;
                    if(name.getText().toString().isEmpty()) {
                        error++;
                        name.setError("insert name");
                    }
                    if(phone.getText().toString().isEmpty()){
                        error++;
                        phone.setError("insert name");
                    }
                    if(email.getText().toString().isEmpty()){
                        email.setText("");
                    }
                    if(error == 0) {
                        contact.addContact(name.getText().toString(), phone.getText().toString(), email.getText().toString(), db);
                        dialog.cancel();
                        Toast.makeText(getBaseContext(),"Contact Ajoute",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Button cancel = dialog.findViewById(R.id.cancel);
            cancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        }
        else if(v.getId() == R.id.list){
            db = contact.getReadableDatabase();
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.lister_contact);
            final ListView list = dialog.findViewById(R.id.listv);
            ArrayList<String> items = contact.listContact(db);
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
            list.setAdapter(adapter);
            dialog.setTitle("Contact");
            dialog.show();
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Dialog dialog2 = new Dialog(view.getContext());
                    dialog2.setContentView(R.layout.ajouter_contact);
                    dialog2.setTitle("update Contact");
                    String listValue =(String) list.getItemAtPosition(position);
                    StringTokenizer strToken = new StringTokenizer(listValue," : ");
                    strToken.nextToken();
                    EditText name = dialog2.findViewById(R.id.name);
                    name.setText(strToken.nextToken());
                    EditText phone = dialog2.findViewById(R.id.phone);
                    phone.setText(strToken.nextToken());
                    EditText email = dialog2.findViewById(R.id.email);
                    email.setText(strToken.nextToken());
                    Button ajouter = dialog2.findViewById(R.id.ajouter);
                    Button cancel = dialog2.findViewById(R.id.cancel);
                    final int pos = position;
                    ajouter.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            db = contact.getWritableDatabase();
                            EditText name = dialog2.findViewById(R.id.name);
                            EditText phone = dialog2.findViewById(R.id.phone);
                            EditText email = dialog2.findViewById(R.id.email);
                            String listValue =(String) list.getItemAtPosition(pos);
                            StringTokenizer strToken = new StringTokenizer(listValue," : ");
                            contact.updateContact(name.getText().toString(),phone.getText().toString(),email.getText().toString(), Integer.parseInt(strToken.nextToken()),db);
                            dialog2.cancel();
                            dialog.cancel();
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            dialog2.cancel();
                        }
                    });
                    ajouter.setText("update");
                    dialog2.show();
                }
            });
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final AlertDialog.Builder dialog_delete = new AlertDialog.Builder(dialog.getContext());
                    dialog_delete.setMessage("supprimer Contact ?");
                    final int pos = position;
                    dialog_delete.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String listValue =(String) list.getItemAtPosition(pos);
                            StringTokenizer strToken = new StringTokenizer(listValue," : ");
                            db = contact.getWritableDatabase();
                            contact.deleteContact(Integer.parseInt(strToken.nextToken()),db);
                            dialog_delete.setCancelable(true);
                            dialog.cancel();
                        }
                    });
                    dialog_delete.setNegativeButton("non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog_delete.setCancelable(true);
                        }
                    });
                    dialog_delete.show();
                    return false;
                }
            });
        }
    }
}
