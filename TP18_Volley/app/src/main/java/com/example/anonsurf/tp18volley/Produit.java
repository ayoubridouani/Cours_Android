package com.example.anonsurf.tp18volley;

public class Produit {
    private String des,ref;

    public Produit(String ref, String des) {
        this.des = des;
        this.ref = ref;
    }

    public String getDes() {
        return des;
    }

    public String getRef() {
        return ref;
    }
}
