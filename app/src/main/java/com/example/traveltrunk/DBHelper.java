package com.example.traveltrunk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "flightcontext.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table flightDataTable(loc TEXT, des TEXT, tim TEXT, date TEXT, airlin TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists flightDataTable");
    }

    public Boolean insertflightdata(String loc, String des, String tim,String date, String airlin)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("loc", loc);
        contentValues.put("des", des);
        contentValues.put("tim", tim);
        contentValues.put("date", date);
        contentValues.put("airlin", airlin);
        long result = DB.insert("flightDataTable", null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from flightDataTable", null);
        return cursor;
    }



    public Boolean deleteflightdata(String loc)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from flightDataTable where loc = ?", new String[]{loc});
        if(cursor.getCount()>0)
        {
            long result = DB.delete("flightDataTable", "loc=?", new String[]{loc});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }

}
