
package com.example.anonsurf.tp14commandeproduit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Model{
    private Database database;
    private SQLiteDatabase db;

    public Model(Context context){
        database = new Database(context);
    }

    public int checkUser(String user, String pass){
        db = database.getReadableDatabase();
        String columns[] = {database.getTABLE_PRODUCTS_ID(), database.getTABLE_USERS_GROUPID()};
        String condition = database.getTABLE_USERS_USERNAME() + " = ? and " + database.getTABLE_USERS_PASSWORD() + " = ?";
        String conditionValue[] = {user,pass};
        Cursor cursor = db.query(database.getTABLE_USERS(),columns,condition,conditionValue,null,null,null);
        int isExist = cursor.getCount();

        if(isExist != 0){
            cursor.moveToNext();
            int groupID = cursor.getInt(cursor.getColumnIndexOrThrow(database.getTABLE_USERS_GROUPID()));
            Controller.setUSERId(cursor.getInt(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_ID())));
            cursor.close();
            db.close();
            return groupID;
        }else{
            cursor.close();
            db.close();
            return isExist;
        }
    }

    public void insertInDB(String values[]){
        db = database.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(database.getTABLE_USERS_FIRSTNAME(),values[0]);
        cValues.put(database.getTABLE_USERS_LASTNAME(),values[1]);
        cValues.put(database.getTABLE_USERS_USERNAME(),values[2]);
        cValues.put(database.getTABLE_USERS_PASSWORD(),values[3]);
        cValues.put(database.getTABLE_USERS_GROUPID(),values[4]);
        db.insert(database.getTABLE_USERS(),null,cValues);
        db.close();
    }

    public void saveProduct(String reference, String designation, String pathImage){
        db = database.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(database.getTABLE_PRODUCTS_REFERENCE(),reference);
        cValues.put(database.getTABLE_PRODUCTS_DESIGNATION(),designation);
        cValues.put(database.getTABLE_PRODUCTS_PATHIMAGE(),pathImage);
        db.insert(database.getTABLE_PRODUCTS(),null,cValues);
        db.close();
    }

    public ArrayList<Product> getAllProducts(){
        db = database.getReadableDatabase();
        ArrayList<Product> data = new ArrayList<>();
        String columns[] = {database.getTABLE_PRODUCTS_ID(),database.getTABLE_PRODUCTS_REFERENCE(),database.getTABLE_PRODUCTS_DESIGNATION(),database.getTABLE_PRODUCTS_PATHIMAGE()};
        Cursor cursor = db.query(database.getTABLE_PRODUCTS(),columns,null,null,null,null,null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_ID()));
            String reference = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_REFERENCE()));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_DESIGNATION()));
            String pathImage = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_PATHIMAGE()));
            data.add(new Product(id,reference,designation,pathImage));
        }

        cursor.close();
        db.close();
        return data;
    }

    public void addToBasket(int client, int product){
        db = database.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(database.getTABLE_COMMANDS_USERS_ID(),client);
        cValues.put(database.getTABLE_COMMANDS_PRODUCTS_ID(),product);
        db.insert(database.getTABLE_COMMANDS(),null,cValues);
        db.close();
    }

    public ArrayList<Product> listeForPanier(int client){
        db = database.getReadableDatabase();
        ArrayList<Product> data = new ArrayList<>();
        String select = "select * FROM " + database.getTABLE_PRODUCTS() + " INNER JOIN " + database.getTABLE_COMMANDS() + " ON "+ database.getTABLE_PRODUCTS_ID() + " = " + database.getTABLE_COMMANDS_PRODUCTS_ID() + " WHERE " + database.getTABLE_COMMANDS_USERS_ID() + " = ?";
        String conditionValue[] = {String.valueOf(client)};
        Cursor cursor = db.rawQuery(select,conditionValue);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_ID()));
            String reference = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_REFERENCE()));
            String designation = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_DESIGNATION()));
            String pathImage = cursor.getString(cursor.getColumnIndexOrThrow(database.getTABLE_PRODUCTS_PATHIMAGE()));
            data.add(new Product(id,reference,designation,pathImage));
        }

        cursor.close();
        db.close();
        return data;
    }
}
