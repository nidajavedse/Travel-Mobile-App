package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    TextView tv,t4;
    ImageButton btn,btn1,btn2,btn3,btn4;
    TextView name;
    LocationDatabase db;
    ImageView profile,sideMenu;
    public ImageView search,chat,notification,chat1,chat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search = (ImageView) findViewById(R.id.imageView12);
        search.setOnClickListener(this);
        chat = (ImageView) findViewById(R.id.imageView13);
        chat.setOnClickListener(this);
        notification = (ImageView) findViewById(R.id.imageView11);
        notification.setOnClickListener(this);
        chat1 = (ImageView) findViewById(R.id.imageView);
        chat1.setOnClickListener(this);
        chat2 = (ImageView) findViewById(R.id.imageView1);
        chat2.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.textView15);
        tv.setOnClickListener(this);
        btn = (ImageButton) findViewById(R.id.p);
        btn.setOnClickListener(this);
        btn1 = (ImageButton) findViewById(R.id.p1);
        btn1.setOnClickListener(this);
        btn2 = (ImageButton) findViewById(R.id.p2);
        btn2.setOnClickListener(this);
        btn3 = (ImageButton) findViewById(R.id.p3);
        btn3.setOnClickListener(this);
        btn4 = (ImageButton) findViewById(R.id.p4);
        btn4.setOnClickListener(this);
        profile = (ImageView) findViewById(R.id.profile);
        profile.setOnClickListener(this);
        sideMenu = (ImageView) findViewById(R.id.imageView14);
        sideMenu.setOnClickListener(this);
        t4 = (TextView) findViewById(R.id.textView28);
        t4.setOnClickListener(this);
        db = new LocationDatabase(this);
        name = (TextView) findViewById(R.id.textView15);
        Cursor cursor = db.getdata();
        while (cursor.moveToNext()) {
            name.setText(cursor.getString(0));

        }
        CircleImageView nav_image = (CircleImageView) findViewById(R.id.profile);
        UserProfileDataBase dbHelper = new UserProfileDataBase(this);

        Cursor c = dbHelper.getUser();
        if(c.getCount()==0){

        }
        else{
            while (c.moveToNext()){
                byte[] imageByte = c.getBlob(5);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                nav_image.setImageBitmap(bitmap);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView12:
                intent= new Intent(HomeActivity.this,FindFlightActivity.class);
                startActivity(intent);
                break;
            case R.id.textView28:
                intent= new Intent(HomeActivity.this,UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView11:
                intent= new Intent(HomeActivity.this,UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView13:
                intent= new Intent(HomeActivity.this,FlightList.class);
                startActivity(intent);
                break;
            case R.id.imageView1:
               break;
            case R.id.textView15:
                intent= new Intent(HomeActivity.this,LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.p:
                intent= new Intent(HomeActivity.this,BookFlight.class);
                startActivity(intent);
                break;
            case R.id.p4:
                intent= new Intent(HomeActivity.this,BookFlight.class);
                startActivity(intent);
                break;
            case R.id.p1:
                intent= new Intent(HomeActivity.this,BookFlight.class);
                startActivity(intent);
                break;
            case R.id.p2:
                intent= new Intent(HomeActivity.this,BookFlight.class);
                startActivity(intent);
                break;
            case R.id.p3:
                intent= new Intent(HomeActivity.this,BookFlight.class);
                startActivity(intent);
                break;
            case R.id.profile:
                intent= new Intent(HomeActivity.this,UserProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView14:
                intent= new Intent(HomeActivity.this,SideMenuActivity.class);
                startActivity(intent);
                break;

            case R.id.SearchView:
                intent= new Intent(HomeActivity.this,SideMenuActivity.class);
                startActivity(intent);
                break;
        }
    }
}