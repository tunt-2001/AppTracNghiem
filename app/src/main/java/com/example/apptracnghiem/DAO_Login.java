package com.example.apptracnghiem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptracnghiem.Database.Database;
import com.example.apptracnghiem.model.LogIn;


public class DAO_Login {
    Database database;

    public DAO_Login(Context context){
        database = new Database(context);
        SQLiteDatabase db = database.getReadableDatabase();

    }
    public void ThemTK(LogIn lg){
        SQLiteDatabase db = database.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", lg.getUsername());
        values.put("password", lg.getPassword());
        db.insert("Login", null,values);
    }
    public boolean testLogIn(String user, String pass){
        SQLiteDatabase db = database.getReadableDatabase();
        String testlg = "select * from Login where username = '"+user+"' and password = '"+pass+"'";
        Cursor cursor = db.rawQuery(testlg,null);
        if(cursor.getCount() != 0){
            return true;
        }else{
            return false;
        }
    }
}
