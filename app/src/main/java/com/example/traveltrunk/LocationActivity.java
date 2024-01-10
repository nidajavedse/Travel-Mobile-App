package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity {
    LocationDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        String[] name = {"Karachi", "Lahore", "Faisalabad", "Rawalpindi", "Gujranwala", "Peshawar", "Multan",
                "Islamabad", "Quetta", "Bahawalpur", "Sargodha", "Gujrat", "Sahiwal", "Hyderabad","Taxila"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);
        db = new LocationDatabase(this);
        ListView et=(ListView) findViewById(R.id.listView1);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), name[position], Toast.LENGTH_SHORT).show();

                Boolean checkinsertdata  = db.insertlocationdata(name[position]);
                if(checkinsertdata==true)
                {
                    Intent intent = new Intent (getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                }
                else
                {
                }

            }
        });



    }
}