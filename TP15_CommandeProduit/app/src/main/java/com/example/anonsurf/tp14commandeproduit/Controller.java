package com.example.anonsurf.tp14commandeproduit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.anonsurf.tp14commandeproduit.admin.ProductMenu;
import com.example.anonsurf.tp14commandeproduit.authentication.Signin;
import com.example.anonsurf.tp14commandeproduit.client.BasketOfProducts;
import com.example.anonsurf.tp14commandeproduit.client.OrdersOptions;
import com.example.anonsurf.tp14commandeproduit.client.ListAllProducts;
import com.example.anonsurf.tp14commandeproduit.authentication.Login;
import com.example.anonsurf.tp14commandeproduit.admin.AddProduct;
import com.example.anonsurf.tp14commandeproduit.admin.ProductList;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Controller implements View.OnClickListener{
    private static Model model;
    private Login main;
    private Signin signin;
    private ProductMenu menu;
    private AddProduct addProduct;
    private OrdersOptions OrdersOptions;

    private static int USER_ID;

    public Controller(Login main, Context context){
        this.main = main;
        model = new Model(context);
    }

    public Controller(Signin signin){
        this.signin = signin;
    }

    public Controller(ProductMenu menu){
        this.menu = menu;
    }

    public Controller(AddProduct addProduct){
        this.addProduct = addProduct;
    }

    public Controller(OrdersOptions OrdersOptions){
        this.OrdersOptions = OrdersOptions;
    }

    public static int getUSERId() {
        return USER_ID;
    }

    public static void setUSERId(int userId) {
        USER_ID = userId;
    }

    @Override
    public void onClick(View v) {
        //Activity Login
        if(v.getId() == R.id.login){
            if(main.getUser().getText().toString().isEmpty()){
                main.getUser().setError("enter username");
            }
            else if(main.getPass().getText().toString().isEmpty()){
                main.getPass().setError("enter password");
            }
            else{
                if(model.checkUser(main.getUser().getText().toString() ,main.getPass().getText().toString()) == 1) {
                    Intent intent = new Intent(main, ProductMenu.class);
                    main.startActivity(intent);
                    main.finish();
                }
                else if(model.checkUser(main.getUser().getText().toString() ,main.getPass().getText().toString()) == 2){
                    Intent intent = new Intent(main, OrdersOptions.class);
                    intent.putExtra("id",USER_ID);
                    main.startActivity(intent);

                    main.finish();
                }
                else {
                    Toast.makeText(main.getBaseContext(),"invalid informations",Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(v.getId() == R.id.signin){
            Intent intent = new Intent(main,Signin.class);
            main.startActivityForResult(intent,20);
        }

        //Activity Signin
        else if(v.getId() == R.id.register){
            if(signin.getFirstname().getText().toString().isEmpty()){
                signin.getFirstname().setError("enter your firstname");
            }
            if(signin.getLastname().getText().toString().isEmpty()){
                signin.getLastname().setError("enter your lastname");
            }
            if(signin.getUsername().getText().toString().isEmpty()){
                signin.getUsername().setError("enter your username");
            }
            if(signin.getPassword().getText().toString().isEmpty()){
                signin.getPassword().setError("enter your password");
            }
            if(!signin.getFirstname().getText().toString().isEmpty() && !signin.getLastname().getText().toString().isEmpty() && !signin.getUsername().getText().toString().isEmpty() && !signin.getPassword().getText().toString().isEmpty()){
                Bundle bundle = new Bundle();
                bundle.putString("firstname",signin.getFirstname().getText().toString());
                bundle.putString("lastname",signin.getLastname().getText().toString());
                bundle.putString("username",signin.getUsername().getText().toString());
                bundle.putString("password",signin.getPassword().getText().toString());

                if(signin.getSpinner().getSelectedItem().toString().equals("Admin"))
                    bundle.putString("groupID","1");
                else
                    bundle.putString("groupID","2");

                Intent intent = new Intent(signin,Login.class);
                intent.putExtras(bundle);
                signin.setResult(21,intent);

                signin.finish();
            }
        }

        //Activity ProductMenu
        else if(v.getId() == R.id.add){
            Intent intent = new Intent(menu, AddProduct.class);
            menu.startActivity(intent);
        }

        else if(v.getId() == R.id.list){
            Intent intent = new Intent(menu, ProductList.class);
            menu.startActivity(intent);
        }

        //AddProduct
        else if(v.getId() == R.id.gallery){
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            addProduct.startActivityForResult(pickPhoto , 146);
        }

        else if(v.getId() == R.id.camera){
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            addProduct.startActivityForResult(takePicture, 147);
        }

        else if(v.getId() == R.id.ajouter_pr){
            if(addProduct.getReference().getText().toString().isEmpty()){
                addProduct.getReference().setError("enter a reference");
            }
            if(addProduct.getDesignation().getText().toString().isEmpty()){
                addProduct.getDesignation().setError("enter a designation");
            }
            if(addProduct.getPreview().getVisibility() != View.VISIBLE){
                Toast.makeText(addProduct.getBaseContext(),"please choice a picture",Toast.LENGTH_SHORT).show();
            }
            if(!addProduct.getReference().getText().toString().isEmpty() && !addProduct.getDesignation().getText().toString().isEmpty() && addProduct.getPreview().getVisibility() == View.VISIBLE){
                model.saveProduct(addProduct.getReference().getText().toString(),addProduct.getDesignation().getText().toString(),addProduct.getImageURI().toString());
                Toast.makeText(addProduct.getBaseContext(),"Product Added",Toast.LENGTH_SHORT).show();
            }
        }

        //Activity Orders Options
        else if(v.getId() ==  R.id.allproduct){
            Intent intent = new Intent(OrdersOptions, ListAllProducts.class);
            OrdersOptions.startActivity(intent);
        }

        else if(v.getId() ==  R.id.basketOfProducts){
            Intent intent = new Intent(OrdersOptions, BasketOfProducts.class);
            OrdersOptions.startActivity(intent);
        }

        else{

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Signin
        if(requestCode == 20 && resultCode == 21){
            Bundle bundle = data.getExtras();
            String values[] = {bundle.getString("firstname"),bundle.getString("lastname"),bundle.getString("username"),bundle.getString("password"),bundle.getString("groupID")};
            model.insertInDB(values);
            Toast.makeText(main.getBaseContext(),"registration successful...",Toast.LENGTH_SHORT).show();
        }

        //AddProduct - Gallery
        if(requestCode == 146 && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            addProduct.getPreview().setVisibility(View.VISIBLE);
            addProduct.getPreview().setImageURI(selectedImage);
            addProduct.setImageURI(selectedImage);
        }

        //AddProduct - Camera
        if(requestCode == 147 && resultCode == RESULT_OK){
            Uri selectedImage = data.getData();
            addProduct.getPreview().setVisibility(View.VISIBLE);
            addProduct.getPreview().setImageURI(selectedImage);
            addProduct.setImageURI(selectedImage);
        }
    }

    public static ArrayList<Product> getAllProducts(){
        return model.getAllProducts();
    }

    public static ArrayList<Product> listeForPanier(){
        return model.listeForPanier(USER_ID);
    }

    public static void addToBasket(int user, int product){
        model.addToBasket(user, product);
    }
}
