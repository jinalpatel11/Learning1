package com.example.learning1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key ,contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String Name,String Contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",Name);
        contentValues.put("contact",Contact);
        contentValues.put("dob",dob);

        long result = DB.insert("Userdetails",null , contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Boolean updateuserdata(String Name,String Contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("contact",Contact);
        contentValues.put("dob",dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{Name});
        if(cursor.getCount() > 0)
        {
            long result = DB.update("Userdetails", contentValues , "name=?", new String[] { Name});

            if(result == -1)
                return false;
            else
                return true;
        }else
            return false;

    }

    public Boolean deleteuserdata(String Name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{Name});
        if(cursor.getCount() > 0)
        {
            long result = DB.delete("Userdetails" , "name=?", new String[] { Name});

            if(result == -1)
                return false;
            else
                return true;
        }else
            return false;

    }

    public Cursor getData(String Name){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Userdetails ",null);
        return cursor;

    }

}
