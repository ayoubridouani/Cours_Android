package example.webService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b_add;
    private EditText ref, des;
    private ListView lv;

    private ArrayList<Produit> arl;
    private AdaptP adaptP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = findViewById(R.id.editText);
        des = findViewById(R.id.editText2);
        b_add = findViewById(R.id.add);
        lv = findViewById(R.id.listView);

        arl = new ArrayList<>();
        adaptP = new AdaptP(this, R.layout.lay_item_prod, arl);
        lv.setAdapter(adaptP);

        b_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {

            String reference = ref.getText().toString();
            String designation = des.getText().toString();
            String url_insertion = "http://192.168.1.2/WebServiceAndroid/Ajout.php?ref=" + reference + "&des=" + designation;

            Asyn_ta con = new Asyn_ta();
            con.execute(url_insertion,ref.getText().toString(),des.getText().toString());

            ref.setText("");
            des.setText("");
        }
    }

    class Asyn_ta extends AsyncTask<String, Void, String> {
        private String ref;
        private String des;

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            ref = strings[1];
            des = strings[2];
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                result = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            arl.add(new Produit("Designation: " + des,"Reference: " + ref));
            adaptP.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }
}