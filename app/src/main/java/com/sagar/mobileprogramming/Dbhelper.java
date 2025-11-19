package com.sagar.mobileprogramming;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Dbhelper extends SQLiteOpenHelper {

    static String name="bca6sem";
    static int version = 1;

    String sql = "CREATE TABLE if not EXISTS \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"address\"\tTEXT,\n" +
            "\t\"phone\"\tTEXT,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"image\"\tBLOB \n" +
            ")";

    public Dbhelper(@Nullable Context context) {
        super(context,name,null,1 );

        getWritableDatabase().execSQL(sql);
    }

    public void insertUser(ContentValues values){
        getWritableDatabase().insert("user","",values);
    }
    @SuppressLint("Range")
    public ArrayList<Userinfo>getUserList(){
        String sql = "Select * from user";
        ArrayList<Userinfo>list = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        while (cursor.moveToNext()){
            Userinfo info = new Userinfo();
            info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.username= cursor.getString(cursor.getColumnIndex("username"));
            info.password= cursor.getString(cursor.getColumnIndex("password"));
            info.email= cursor.getString(cursor.getColumnIndex("email"));
//            info.id= cursor.getString(cursor.getColumnIndex("id"));
//            info.id= cursor.getString(cursor.getColumnIndex("id"));
//            info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.image= cursor.getBlob(cursor.getColumnIndex("image"));
            list.add(info);

        }
        cursor.close();
        return list;


    }
    @SuppressLint("Range")
    public  Userinfo getUserInfo(String id){
        String sql = "Select * from user where id="+id;

        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        Userinfo info = new   Userinfo();
        while (cursor.moveToNext()){
            info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.username= cursor.getString(cursor.getColumnIndex("username"));
            info.password= cursor.getString(cursor.getColumnIndex("password"));
            info.email= cursor.getString(cursor.getColumnIndex("email"));
            info.gender= cursor.getString(cursor.getColumnIndex("gender"));
//            info.id= cursor.getString(cursor.getColumnIndex("id"));
//            info.id= cursor.getString(cursor.getColumnIndex("id"));
            info.image= cursor.getBlob(cursor.getColumnIndex("image"));

        }
        cursor.close();
        return info;


    }

    public void deleteUser(String id){
        getWritableDatabase().delete("user","id="+id,null);
//        getWritableDatabase().delete("user","id=?",new String[]{id});
    }

    public void updateUser(String id, ContentValues values){
        getWritableDatabase().update("user",values,"id=?",new String[]{id});
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
