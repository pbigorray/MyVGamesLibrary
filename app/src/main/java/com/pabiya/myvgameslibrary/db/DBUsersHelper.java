package com.pabiya.myvgameslibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUsersHelper extends SQLiteOpenHelper {
    public static final String DBNAME="login.db";
    private SQLiteDatabase db =this.getWritableDatabase();


    public DBUsersHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (user TEXT primary key, password TEXT,email TEXT, admin NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
    }


    public boolean insertData(String user,String password,String email, int admin){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("user",user);
        values.put("password",password);
        values.put("email",email);
        values.put("admin",admin);


        long result=db.insert("users",null,values);
        return result != 0;
    }

    public boolean checkUser(String user){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where user=?",new String[]{user});
        return cursor.getCount() <= 0;
    }
    public boolean checkUserPass(String user,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where user=? and password=?",new String[]{user,password});
        return cursor.getCount() <= 0;
    }
    public boolean checkAdminUser(String user){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where user=? and admin=1",new String[]{user});
        return cursor.getCount() <= 0;
    }
    public void dbClose(){
        db.close();
    }
}
