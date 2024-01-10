package com.example.traveltrunk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationDatabase extends SQLiteOpenHelper {
    public LocationDatabase(Context context) {
        super(context, "LocationContext.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table locationDataTable(location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists locationDataTable");
    }

    public Boolean insertlocationdata(String location) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location", location);
        long result = DB.insert("locationDataTable", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from locationDataTable", null);
        return cursor;
    }
}


