package com.example.anonsurf.tp14commandeproduit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shopping";
    private static final int DATABASE_VERSION = 1;

    private final String TABLE_USERS = "users";
    private final String TABLE_USERS_ID = "id";
    private final String TABLE_USERS_FIRSTNAME = "firstname";
    private final String TABLE_USERS_LASTNAME = "lastname";
    private final String TABLE_USERS_USERNAME = "username";
    private final String TABLE_USERS_PASSWORD = "password";
    private final String TABLE_USERS_GROUPID = "groupID";//1=admin,2=user
    private final String CREATE_TABLE_USERS = "create table " + TABLE_USERS + "( " + TABLE_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_USERS_FIRSTNAME + " TEXT NOT NULL, " + TABLE_USERS_LASTNAME + " TEXT NOT NULL, " + TABLE_USERS_USERNAME + " TEXT NOT NULL, " + TABLE_USERS_PASSWORD  + " TEXT NOT NULL, " + TABLE_USERS_GROUPID + " INTEGER)";
    private final String DROP_TABLE_USERS = "drop table if exists " + TABLE_USERS;

    private final String TABLE_PRODUCTS = "product";
    private final String TABLE_PRODUCTS_ID = "id";


    private final String TABLE_PRODUCTS_REFERENCE = "reference";
    private final String TABLE_PRODUCTS_DESIGNATION = "designation";
    private final String TABLE_PRODUCTS_PATHIMAGE = "pathimage";
    private final String CREATE_TABLE_PRODUCTS = "create table " + TABLE_PRODUCTS + "( " + TABLE_PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_PRODUCTS_REFERENCE + " TEXT NOT NULL, " + TABLE_PRODUCTS_DESIGNATION + " TEXT NOT NULL, " + TABLE_PRODUCTS_PATHIMAGE + " TEXT NOT NULL)";
    private final String DROP_TABLE_PRODUCTS = "drop table if exists " + TABLE_PRODUCTS;

    private final String TABLE_COMMANDS = "commande";
    private final String TABLE_COMMANDS_USERS_ID = "idUsers";
    private final String TABLE_COMMANDS_PRODUCTS_ID = "idProducts";
    private final String CREATE_TABLE_COMMANDS = "create table " + TABLE_COMMANDS + "( " + TABLE_COMMANDS_USERS_ID + " INTEGER, " + TABLE_COMMANDS_PRODUCTS_ID + " INTEGER, "
                                                + " FOREIGN KEY (" + TABLE_COMMANDS_USERS_ID + ") REFERENCES " + TABLE_USERS + " (" + TABLE_USERS_ID + ") ON DELETE CASCADE ON UPDATE NO ACTION,"
                                                + " FOREIGN KEY (" + TABLE_COMMANDS_PRODUCTS_ID + ") REFERENCES " + TABLE_PRODUCTS + " (" + TABLE_PRODUCTS_ID + ") ON DELETE CASCADE ON UPDATE NO ACTION)";
    private final String DROP_TABLE_COMMANDS = "drop table if exists " + TABLE_COMMANDS;

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_COMMANDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USERS);
        db.execSQL(DROP_TABLE_PRODUCTS);
        db.execSQL(DROP_TABLE_COMMANDS);
    }

    public String getTABLE_USERS() {
        return TABLE_USERS;
    }

    public String getTABLE_USERS_ID() {
        return TABLE_USERS_ID;
    }

    public String getTABLE_USERS_FIRSTNAME() {
        return TABLE_USERS_FIRSTNAME;
    }

    public String getTABLE_USERS_LASTNAME() {
        return TABLE_USERS_LASTNAME;
    }

    public String getTABLE_USERS_USERNAME() {
        return TABLE_USERS_USERNAME;
    }

    public String getTABLE_USERS_PASSWORD() {
        return TABLE_USERS_PASSWORD;
    }

    public String getTABLE_USERS_GROUPID() {
        return TABLE_USERS_GROUPID;
    }

    public String getTABLE_PRODUCTS() {
        return TABLE_PRODUCTS;
    }

    public String getTABLE_PRODUCTS_ID() {
        return TABLE_PRODUCTS_ID;
    }

    public String getTABLE_PRODUCTS_REFERENCE() {
        return TABLE_PRODUCTS_REFERENCE;
    }

    public String getTABLE_PRODUCTS_DESIGNATION() {
        return TABLE_PRODUCTS_DESIGNATION;
    }

    public String getTABLE_PRODUCTS_PATHIMAGE() {
        return TABLE_PRODUCTS_PATHIMAGE;
    }

    public String getTABLE_COMMANDS() {
        return TABLE_COMMANDS;
    }

    public String getTABLE_COMMANDS_USERS_ID() {
        return TABLE_COMMANDS_USERS_ID;
    }

    public String getTABLE_COMMANDS_PRODUCTS_ID() {
        return TABLE_COMMANDS_PRODUCTS_ID;
    }
}
