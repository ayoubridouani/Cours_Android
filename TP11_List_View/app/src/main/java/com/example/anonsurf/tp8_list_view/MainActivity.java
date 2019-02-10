package com.example.anonsurf.tp8_list_view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView text1,text2;
    private Button btn1,btn2;
    private ListView lv;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.editText);
        text2 = findViewById(R.id.editText2);

        btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        lv = findViewById(R.id.listv);

        list = new ArrayList<>();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);


        lv.setAdapter(adapter);

        /*
        //modifier les valeurs
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = list.get(position);
                text1.setText(value);
            }
        });
        */
        /*
        //supprimer l'element a partir de cliquer eu
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = list.get(position);
                list.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
            }
        });
        */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int cc = position;
                final AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                /*ab.setTitle("confirmation");
                ab.setMessage("supprimer element ?");
                ab.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(cc);
                        adapter.notifyDataSetChanged();
                    }
                });
                ab.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });*/

                View viewk = LayoutInflater.from(MainActivity.this).inflate(R.layout.tesla, null);

                text = viewk.findViewById(R.id.editText3);
                text.setText(list.get(cc));

                Button bntt1 = viewk.findViewById(R.id.button4);
                final Button bntt2 = viewk.findViewById(R.id.button5);
                bntt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.set(cc,text.getText().toString());
                        adapter.notifyDataSetChanged();
                        ab.setCancelable(false);
                    }
                });
                bntt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(cc);
                        bntt2.setEnabled(false);
                        adapter.notifyDataSetChanged();
                        ab.setCancelable(false);
                    }
                });

                ab.setView(viewk);
                ab.show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            list.add(text1.getText().toString());
            text1.setText("");
            adapter.notifyDataSetChanged();
        }
        if(v.getId() == R.id.button2){
            //list.remove(text1.getText().toString());
            list.clear();
            text1.setText("");
            adapter.notifyDataSetChanged();
        }
    }
}
