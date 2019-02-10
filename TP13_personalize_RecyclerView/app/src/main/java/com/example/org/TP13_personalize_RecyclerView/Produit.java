package com.example.org.TP13_personalize_RecyclerView;

import android.graphics.Bitmap;

public class Produit {
    private String ref, des;
    private String ch;
    private Bitmap bitmap;

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getRef() {
        return ref;
    }

    public String getDes() {
        return des;
    }

    public String getCh() {
        return ch;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Produit(String ref, String des, String ch) {
        this.ref = ref;
        this.des = des;
        this.ch = ch;
    }

    public Produit(String ref, String des, String ch, Bitmap bitmap) {

        this.ref = ref;
        this.des = des;
        this.ch = ch;
        this.bitmap = bitmap;
    }
}
