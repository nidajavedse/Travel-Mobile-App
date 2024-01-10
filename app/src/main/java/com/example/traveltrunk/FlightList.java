package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;

public class FlightList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> loc, des, tim,date,airlin;
    DBHelper DB;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);
        DB = new DBHelper(this);
        loc = new ArrayList<>();
        des = new ArrayList<>();
        tim = new ArrayList<>();
        date = new ArrayList<>();
        airlin = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, loc, des, tim,date,airlin);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(FlightList.this, "No Data Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                loc.add(cursor.getString(0));
                des.add(cursor.getString(1));
                tim.add(cursor.getString(2));
                date.add(cursor.getString(3));
                airlin.add(cursor.getString(4));
            }
        }
    }
}