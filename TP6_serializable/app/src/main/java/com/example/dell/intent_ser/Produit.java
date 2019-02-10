package com.example.dell.intent_ser;

import java.io.Serializable;

public class Produit implements Serializable {
    private String name;
    private String prix;

    public Produit(String n,String p) {
        this.name = n;
        this.prix = p;
    }

    public void setName(String n)
    {
        this.name = n;
    }
    public void setPrix(String p)
    {
        this.prix = p;
    }

    public String getName()
    {
        return  this.name;
    }

    public String getPrix()
    {
        return  this.prix;
    }
}


