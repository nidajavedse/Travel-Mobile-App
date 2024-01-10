package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveltrunk.UserProfileDataBase;

import de.hdodenhof.circleimageview.CircleImageView;

public class SideMenuActivity extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    public TextView textview1, textview2, textview3, textview4, textview5,textview6, textview7;
    ImageView cross;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);


        textview1 = (TextView) findViewById(R.id.textViewhome);
        textview1.setOnClickListener(this);
        textview2 = (TextView) findViewById(R.id.textViewfind);
        textview2.setOnClickListener(this);
        textview3 = (TextView) findViewById(R.id.textViewbooked);
        textview3.setOnClickListener(this);
        textview4 = (TextView) findViewById(R.id.textViewprofile);
        textview4.setOnClickListener(this);
        textview7 = (TextView) findViewById(R.id.textViewlogout);
        textview7.setOnClickListener(this);
        cross = (ImageView) findViewById(R.id.imageView28);
        cross.setOnClickListener(this);

        CircleImageView nav_image = (CircleImageView) findViewById(R.id.user);
        TextView nav_userName = (TextView) findViewById(R.id.textView);

        UserProfileDataBase dbHelper = new UserProfileDataBase(this);

        Cursor cursor = dbHelper.getUser();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No enteies", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                nav_userName.setText("" + cursor.getString(0));
                byte[] imageByte = cursor.getBlob(5);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                nav_image.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageView28:
                intent = new Intent(SideMenuActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.textViewhome:
                intent = new Intent(SideMenuActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.textViewfind:
                intent = new Intent(SideMenuActivity.this, FindFlightActivity.class);
                startActivity(intent);
                break;
            case R.id.textViewbooked:
                intent = new Intent(SideMenuActivity.this, FlightList.class);
                startActivity(intent);
                break;
            case R.id.textViewprofile:
                intent= new Intent(SideMenuActivity.this,UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.textViewlogout:
                intent = new Intent(SideMenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(SideMenuActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}