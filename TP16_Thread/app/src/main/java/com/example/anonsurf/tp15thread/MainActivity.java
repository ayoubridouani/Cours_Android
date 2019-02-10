package com.example.anonsurf.tp15thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//Reference:
//http://www.hendiware.com/%D8%A7%D9%84%D9%80-asynctask-%D8%A7%D9%84%D8%A8%D8%AF%D9%8A%D9%84-%D8%A7%D9%84%D8%A8%D8%B3%D9%8A%D8%B7-%D9%84%D9%84%D9%80-thread-%D9%81%D9%89-%D8%A7%D9%84%D8%A3%D9%86%D8%AF%D8%B1%D9%88%D9%8A%D8%AF
//http://www.zoftino.com/android-progressbar-and-custom-progressbar-examples

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private Button btn;
    private ProgressBar pb;
    private TextView tv;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=findViewById(R.id.editText);
        btn=findViewById(R.id.button);
        pb=findViewById(R.id.progressBar);
        tv=findViewById(R.id.textView);
        bar=findViewById(R.id.progressBarH);

        pb.setVisibility(View.INVISIBLE);
        bar.setProgress(0);
        bar.setMax(100);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int val = Integer.parseInt(et.getText().toString());

                /*
                //without thread
                pb.setVisibility(View.VISIBLE);
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                pb.setVisibility(View.INVISIBLE);
                tv.setText("Result: " + String.valueOf(val));
                */

                //with thread
                MonAsyncTask task =new MonAsyncTask();
                task.execute(val,val);
            }
        });

    }

    class MonAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this,"Préparation...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int v = integers[0] + integers[1];

            for(int i=0;i<5;i++) {
                try {
                    Thread.sleep(5000);
                    publishProgress((i+1)*20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return "Result: " + String.valueOf(v);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_SHORT).show();
        }
    }
}

