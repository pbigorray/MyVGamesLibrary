package com.pabiya.myvgameslibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pabiya.myvgameslibrary.activitys.Game;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBGamesHelper extends SQLiteOpenHelper {
    public static final String DBNAME="game.db";
    private SQLiteDatabase db =this.getWritableDatabase();


    public DBGamesHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table games (id NUMERIC primary key, name TEXT,gender TEXT,price REAL, alquilado NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists games");
    }
    private void addData(){
        insertData(1,"Stardew valley","simulation", 2.50f,false);
        insertData(2,"Minecraft","sandbox", 20.50f,false);
        insertData(3,"Carlos Duty","PVP", 30.50f,false);
        insertData(4,"LOL","Depresion", 0f,false);

    }
    public boolean insertData(int id,String name,String gender,float price, boolean alquilado){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("id",id);
        values.put("name",name);
        values.put("gender",gender);
        values.put("price",price);
        values.put("alquilado",alquilado);

        long result=db.insert("users",null,values);
        return result != 0;
    }

//    public boolean checkUser(String user){
//        SQLiteDatabase db =this.getWritableDatabase();
//        Cursor cursor=db.rawQuery("select * from games where user=?",new String[]{user});
//        return cursor.getCount() <= 0;
//    }
//    public boolean checkUserPass(String user,String password){
//        SQLiteDatabase db =this.getWritableDatabase();
//        Cursor cursor=db.rawQuery("select * from games where user=? and password=?",new String[]{user,password});
//        return cursor.getCount() <= 0;
//    }
    public List<Game> getAll(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from games",null);

        ArrayList<Game> gameList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                gameList.add(new Game(cursor.getInt(1), cursor.getString(2),cursor.getString(3),cursor.getFloat(4),cursor.getInt(5)));
            } while (cursor.moveToNext());

        }

        cursor.close();
        return gameList;
    }

}
