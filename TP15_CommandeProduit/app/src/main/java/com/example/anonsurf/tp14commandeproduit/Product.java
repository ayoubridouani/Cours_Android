package com.example.anonsurf.tp14commandeproduit;

public class Product {
    private int id;
    private String reference;
    private String designation;
    private String pathImage;

    public Product(int id, String reference, String designation, String pathImage) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.pathImage = pathImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }
}
