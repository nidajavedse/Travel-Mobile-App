package com.example.traveltrunk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class CreditCardDataBase extends SQLiteOpenHelper {

    public static final String DBNAME = "bank.db";

    public CreditCardDataBase(Context context) {
        super(context, "bank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table creditCard(cardNo TEXT primary key,name Text, cvv NUMERIC,date DATE , amount NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists creditCard");
    }

    public Boolean insertData(String cardNo, String name, String cvv ,String date , String amount ){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("cardNo", cardNo);
        contentValues.put("name", name);
        contentValues.put("cvv", cvv);
        contentValues.put("date", date);
        contentValues.put("amount", amount);
        long result = MyDB.insert("creditCard", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkcardNo(String cardNo) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from creditCard where cardNo = ?", new String[]{cardNo});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkname(String cardNo, String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from creditCard where cardNo = ? and name = ?", new String[] {cardNo,name});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkcvv(String cardNo, String name, String cvv){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from creditCard where cardNo = ? and name = ? and cvv = ? ", new String[] {cardNo,name,cvv});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkdate(String cardNo, String name, String cvv ,String date){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from creditCard where cardNo = ? and name = ? and cvv = ?  and date = ? ", new String[] {cardNo,name,cvv,date});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkamount(String cardNo, String name, String cvv ,String date,String amount){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from creditCard where cardNo = ? and name = ? and cvv = ?  and date = ? and amount = ? ", new String[] {cardNo,name,cvv,date,amount});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}