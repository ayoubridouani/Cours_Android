package com.example.anonsurf.calculatrice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private Button btn1,btn2,btn3,btn4;

    class Test{
        private Button view;
        private int n1;
        private int n2;

        Test(Button v, final String op) {
            this.view = v;

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    n1 = Integer.parseInt(editText1.getText().toString());
                    n2 = Integer.parseInt(editText2.getText().toString());

                    if (op == "+") textView.setText(String.valueOf(n1 + n2));
                    else if (op == "-") textView.setText(String.valueOf(n1 - n2));
                    else if (op == "*") textView.setText(String.valueOf(n1 * n2));
                    else textView.setText(String.valueOf(n1 / n2));
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.num1);
        editText2 = findViewById(R.id.num2);
        textView = findViewById(R.id.res);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        new Test(btn1,"+");
        new Test(btn2,"-");
        new Test(btn3,"*");
        new Test(btn4,"/");
    }
}
