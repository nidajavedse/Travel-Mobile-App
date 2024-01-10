package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FindFlightActivity extends AppCompatActivity implements View.OnClickListener{
    Intent intent;
    public ImageView imageview1, imageview2, imageview3, imageview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_flight);

        //set location


        Button button = (Button) findViewById(R.id.allbtn);
        Button buttonBookFlight = (Button) findViewById(R.id.buttonbookflight);
        //  button.setOnClickListener(this);
         imageview1 = (ImageView) findViewById(R.id.imageView4);
         imageview1.setOnClickListener(this);
         imageview2 = (ImageView) findViewById(R.id.imageView6);
         imageview2.setOnClickListener(this);
         imageview3 = (ImageView) findViewById(R.id.imageView7);
         imageview3.setOnClickListener(this);
         imageview4 = (ImageView) findViewById(R.id.imageView8);
         imageview4.setOnClickListener(this);
        //   imageview.setOnClickListener(FindFlightActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ListOfFlights.class);
                startActivity(intent);
            }
        });


      buttonBookFlight.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {

            Intent intent = new Intent(FindFlightActivity.this, BookFlight.class);
            startActivity(intent);
        }
    });

}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageView4:
                intent = new Intent(FindFlightActivity.this, BookFlight.class);
                startActivity(intent);
                break;
            case R.id.imageView6:
            case R.id.imageView7:
            case R.id.imageView8:
                intent = new Intent(FindFlightActivity.this, BookFlight.class);
                startActivity(intent);
                break;
        }
    }


}