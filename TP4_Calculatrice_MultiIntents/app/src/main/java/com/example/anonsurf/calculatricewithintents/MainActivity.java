package com.example.anonsurf.calculatricewithintents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private EditText editText1,editText2;
    private Button plus,moins,fois,div,lance;
    private TextView textView;

    public static void setChoice(String choice) {
        MainActivity.choice = choice;
    }

    private static String choice="";

    public void FirstColor() {
        plus.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        moins.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        fois.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        div.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.nbr1);
        editText2 = findViewById(R.id.nbr2);
        textView = findViewById(R.id.result);

        plus = findViewById(R.id.plus);
        moins = findViewById(R.id.moins);
        fois = findViewById(R.id.fois);
        div = findViewById(R.id.div);
        lance = findViewById(R.id.lance);

        FirstColor();

        plus.setOnClickListener(new CalculListner("plus"));
        moins.setOnClickListener(new CalculListner("moins"));
        fois.setOnClickListener(new CalculListner("fois"));
        div.setOnClickListener(new CalculListner("div"));

        lance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CibleActivity.class);
                if(editText1.getText().toString().isEmpty())
                    editText1.setError("Enter a Number");
                else if(editText2.getText().toString().isEmpty())
                    editText2.setError("Enter a Number");
                else{
                    int nbr1 = Integer.parseInt(editText1.getText().toString());
                    int nbr2 = Integer.parseInt(editText2.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putInt("nombre1", nbr1);
                    bundle.putInt("nombre2", nbr2);
                    if(choice.isEmpty()) {
                        Toast.makeText(getBaseContext(),"Please choice an operation",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        bundle.putString("operation", choice);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 12);
                    }

                }
            }
        });
    }

    class CalculListner implements View.OnClickListener{
        private String type;

        CalculListner(String type){
            this.type = type;
        }

        @Override
        public void onClick(View v){
            setChoice(this.type);
            FirstColor();
            v.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 12 && resultCode == 44) {
            super.onActivityResult(requestCode, resultCode, data);
            Bundle b = data.getExtras();
            if(b.getString("error").equals("Operation Error")) textView.setText(b.getString("error"));
            else {
                int res= b.getInt("result");
                textView.setText("Le resultat est: " + Integer.toString(res));
            }
        }
    }
}