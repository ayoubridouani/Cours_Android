package exercice3.android.tp.tp3implicite2_app2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Cible1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cible1);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + getIntent().getDataString()));
        startActivity(intent);
    }
}
