package com.example.anonsurf.calculatrice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText field1;
    private EditText field2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = findViewById(R.id.number1);
        field2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);

    }

    public void calc(View view){
        int num1 = Integer.parseInt(field1.getText().toString());
        int num2 = Integer.parseInt(field2.getText().toString());
        switch(view.getId()){
            case R.id.add:
                result.setText("Result : " + Integer.toString((num1+num2)));
                break;
            case R.id.sub:
                result.setText("Result : " + Integer.toString((num1-num2)));
                field2.clearFocus();
                break;
            case R.id.mul:
                result.setText("Result : " + Integer.toString((num1*num2)));
                break;
            case R.id.div:
                if(num2 == 0) result.setText("Division Par Zero ?? ");
                else result.setText("Result : " + Integer.toString((num1/num2)));
                break;
        }
    }
}
