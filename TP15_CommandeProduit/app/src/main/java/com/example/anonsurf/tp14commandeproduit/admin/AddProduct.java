package com.example.anonsurf.tp14commandeproduit.admin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.anonsurf.tp14commandeproduit.Controller;
import com.example.anonsurf.tp14commandeproduit.R;

public class AddProduct extends AppCompatActivity {
    private EditText reference;
    private EditText designation;
    private Button gallery;
    private Button camera;
    private Button add_produit;
    private ImageView preview;

    private Uri ImageURI;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        controller = new Controller(this);

        reference = this.findViewById(R.id.ref);
        designation = this.findViewById(R.id.des);
        gallery = this.findViewById(R.id.gallery);
        camera = this.findViewById(R.id.camera);
        add_produit = this.findViewById(R.id.ajouter_pr);
        preview = this.findViewById(R.id.preview);

        gallery.setOnClickListener(controller);
        camera.setOnClickListener(controller);
        add_produit.setOnClickListener(controller);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        controller.onActivityResult(requestCode, resultCode, data);
    }

    public EditText getReference() {
        return reference;
    }

    public EditText getDesignation() {
        return designation;
    }

    public ImageView getPreview() {
        return preview;
    }

    public void setImageURI(Uri ImageURI_v) {
        ImageURI = ImageURI_v;
    }

    public Uri getImageURI() {
        return ImageURI;
    }
}
