package com.example.anonsurf.advancedintent;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2,btn3,btn4,btn5,btn6;
    ImageView img;
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

        text1 = findViewById(R.id.editText);

        img = findViewById(R.id.imageButton);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        img.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==44 && resultCode==Activity.RESULT_OK){
            img.setImageBitmap((Bitmap)data.getExtras().get("data"));
        }
    }

    @Override
    public void onClick(View v){
        Intent intent;
        String value = text1.getText().toString();
        switch(v.getId()){
            case R.id.button1:
                if(!value.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + value));
                    startActivity(intent);
                }else{
                    text1.setError("insert something");
                }
                break;
            case R.id.button2:
                if(!value.isEmpty()) {
                    intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + value));
                    startActivity(intent);
                }else{
                    text1.setError("insert something");
                }
                break;
            case R.id.button3:
                intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people"));
                startActivity(intent);
                break;
            case R.id.button4:
                if(!value.isEmpty()){
                    intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://" + value));
                    startActivity(intent);
                }else{
                    text1.setError("insert something");
                }
                break;
            case R.id.button5:
                if(!value.isEmpty()) {
                    intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, value);
                    startActivity(intent);
                }else{
                    text1.setError("insert something");
                }
                break;
            case R.id.button6:
                if(!value.isEmpty()) {
                    intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value));
                    startActivity(intent);
                }else{
                    text1.setError("insert something");
                }
                break;
            case R.id.imageButton:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,44);
        }
    }
}