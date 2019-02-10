package com.example.anonsurf.calculatricewithintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CibleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cible);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String oper = b.getString("operation");
        int nbr1 = b.getInt("nombre1");
        int nbr2 = b.getInt("nombre2");
        int result = 0;

        b.putString("error","No Error");

        switch (oper){
            case "plus":
                result = nbr1 + nbr2;
                break;
            case "moins":
                result = nbr1 - nbr2;
                break;
            case "fois":
                result = nbr1 * nbr2;
                break;
            case "div":
                if(nbr2 == 0)
                    b.putString("error","Operation Error");
                else
                    result = nbr1 / nbr2;
                break;
        }

        b.putInt("result",result);
        intent.putExtras(b);
        setResult(44,intent);

        finish();
    }
}
