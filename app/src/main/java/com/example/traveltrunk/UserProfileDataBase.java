package com.example.traveltrunk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class UserProfileDataBase extends SQLiteOpenHelper {

    Context context;
    public static String DB_NAME = "user.db";
    private static int DB_VERSION = 1;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;


    private static String createTable = "create Table uprofile(userName TEXT"+
            ",firstName TEXT"+
            ",lastName TEXT"+
            ",dob DATE"+
            ",city TEXT"+
            ",image BLOB)";

    public UserProfileDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL(createTable);
        Toast.makeText(context.getApplicationContext(), "created Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
    }

    public void storeData(ModelClass modelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = modelClass.getProfileImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG , 100, byteArrayOutputStream);

        imageInBytes = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", modelClass.getUserName());
        contentValues.put("firstName", modelClass.getFirstName());
        contentValues.put("lastName", modelClass.getLastName());
        contentValues.put("dob", modelClass.getDob());
        contentValues.put("city", modelClass.getCity());
        contentValues.put("image", imageInBytes);

        long result = db.insert("uprofile", null, contentValues);
        if (result != -1) {

            Toast.makeText(context.getApplicationContext(), "Added Successfully", Toast.LENGTH_SHORT).show();
            db.close();
        } else {
            Toast.makeText(context.getApplicationContext(), "Fail to add", Toast.LENGTH_SHORT).show();
        }
    }


    public Cursor getUser() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from uprofile",null);
        return cursor;
    }
}