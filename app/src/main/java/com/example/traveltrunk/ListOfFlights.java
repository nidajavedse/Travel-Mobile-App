package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ListOfFlights extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    public ImageView imageview1, imageview2, imageview3, imageview4, imageview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_flights);

        imageview1 = (ImageView) findViewById(R.id.imageView15);
        imageview1.setOnClickListener(this);
        imageview2 = (ImageView) findViewById(R.id.imageView19);
        imageview2.setOnClickListener(this);
        imageview3 = (ImageView) findViewById(R.id.imageView);
        imageview3.setOnClickListener(this);
        imageview4 = (ImageView) findViewById(R.id.imageView1);
        imageview4.setOnClickListener(this);
        imageview5 = (ImageView) findViewById(R.id.imageView12);
        imageview5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageView15:
            case R.id.imageView19:
            case R.id.imageView:
            case R.id.imageView1:
            case R.id.imageView12:
                intent = new Intent(ListOfFlights.this, BookFlight.class);
                startActivity(intent);
                break;
        }
    }
}