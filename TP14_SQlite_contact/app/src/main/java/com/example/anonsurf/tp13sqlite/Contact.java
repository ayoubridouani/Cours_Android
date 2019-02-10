package com.example.anonsurf.tp13sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Contact extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Contactk";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "contact";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_EMAIL = "email";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( " + COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME_NAME + " text,"
            + COLUMN_NAME_PHONE + " text,"
            + COLUMN_NAME_EMAIL + " text )";
    private static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

    Contact(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.d("Create Database","Database created ...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d("Create table","table created ...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        Log.d("Delete table","table deleted ...");
    }

    public void addContact(String name,String phone,String email,SQLiteDatabase db){
        ContentValues value = new ContentValues();
        value.put(COLUMN_NAME_NAME,name);
        value.put(COLUMN_NAME_PHONE,phone);
        value.put(COLUMN_NAME_EMAIL,email);
        db.insert(TABLE_NAME,null,value);
    }
    public ArrayList<String> listContact(SQLiteDatabase db){
        String[] columns = {Contact.COLUMN_NAME_ID,Contact.COLUMN_NAME_NAME,Contact.COLUMN_NAME_PHONE,Contact.COLUMN_NAME_EMAIL};
        Cursor cursor = db.query(Contact.TABLE_NAME,columns,null,null,null,null,null);
        ArrayList<String> items = new ArrayList<>();
        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME_PHONE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME_EMAIL));
            items.add(String.valueOf(id) + " : " + name + " : " + phone + " : " + email);
        }
        return items;
    }
    public void updateContact(String name,String phone,String email,int position,SQLiteDatabase db){
        ContentValues value = new ContentValues();
        value.put(COLUMN_NAME_NAME,name);
        value.put(COLUMN_NAME_PHONE,phone);
        value.put(COLUMN_NAME_EMAIL,email);
        String where = COLUMN_NAME_ID + " = ?";
        String[] valueWhere = {String.valueOf(position)};
        db.update(TABLE_NAME,value,where,valueWhere);
    }
    public void deleteContact(int position,SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE " + COLUMN_NAME_ID + " = '"+position+"'");
    }
}
