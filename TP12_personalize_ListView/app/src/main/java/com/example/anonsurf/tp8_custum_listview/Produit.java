package com.example.anonsurf.tp8_custum_listview;

import android.graphics.Bitmap;

public class Produit {
    private String reference;
    private String destinataire;
    private Bitmap image;

    public Produit(String reference, String destinataire, Bitmap image) {
        this.reference = reference;
        this.destinataire = destinataire;
        this.image = image;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
