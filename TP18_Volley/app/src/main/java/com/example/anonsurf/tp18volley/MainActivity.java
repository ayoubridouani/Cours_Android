package com.example.anonsurf.tp18volley;

//user:93ad52cb4c@mailox.fun
//pass:93ad52cb4c@mailox.fun

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b_add, b_delete, b_list;
    EditText ref, des;
    ListView lv;

    private ArrayList<Produit> arl;
    AdaptP adaptP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = findViewById(R.id.editText);
        des = findViewById(R.id.editText2);
        b_add = findViewById(R.id.add);

        arl = new ArrayList<>();
        adaptP = new AdaptP(this, R.layout.lay_item_prod, arl);
        lv.setAdapter(adaptP);

        b_list.setOnClickListener(this);
        b_add.setOnClickListener(this);
        b_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String reference = ref.getText().toString();
        String designation = des.getText().toString();
        String url = "http://unscanned-solvents.000webhostapp.com/";
        ref.setText("");
        des.setText("");

        if (view.getId() == R.id.add) {
            if(!reference.isEmpty() && !designation.isEmpty()) {
                url += "Ajout.php?ref=" + reference + "&des=" + designation;

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(request);
            }else{
                ref.setError("insert a reference");
                des.setError("insert a designation");
            }
        }
        else if(view.getId() == R.id.list){
            url += "selection.php";

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONArray array;
                    try {
                        array = new JSONArray(response);
                        arl.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject enreg = array.getJSONObject(i);
                            arl.add(new Produit(enreg.getString("ref"), enreg.getString("des")));
                        }
                        adaptP.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            queue.add(request);
        }
        /*else if(view.getId()==R.id.delete){
            if(!reference.isEmpty()) {
                url += "Suppression.php?ref=" + reference;

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }else{
                ref.setError("insert a reference");
            }
        }*/
        //with method post
        else if(view.getId() == R.id.delete){
            if(!reference.isEmpty()) {
                url += "Suppression.php?ref=" + reference;

                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> par = new HashMap<>();
                        par.put("ref", reference);
                        return par;
                    }
                };
                queue.add(request);
            }else{
                ref.setError("insert a reference");
            }
        }
    }
}