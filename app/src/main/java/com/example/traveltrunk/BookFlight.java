package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookFlight extends AppCompatActivity {

    Button insert;
    AutoCompleteTextView loc,des, tim,airlin;
    EditText date;
    DBHelper DB;
    CalendarView calendarView;
    EditText editText;
    ImageView iv;
    int m,d,y;

    AutoCompleteTextView actv;
    AutoCompleteTextView actv1;
    AutoCompleteTextView actv2;
    AutoCompleteTextView actv3;
    String[] location = {"ISLAMABAD","LAHORE","KARACHI"};
    String[] destination = {"DUBAI","PARIS","ISTANBUL"};
    String[] time = {"10:45 - 14:00","13:45 - 17:00","7:45 - 10:00"};
    String[] airline = {"Turkish Airline","Qatar Airways","American Airlines"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight2);

        // insert data
        loc = findViewById(R.id.autoCompleteTextViewloc);
        des = findViewById(R.id.autoCompleteTextViewdes);
        tim = findViewById(R.id.autoCompleteTextViewtime);
        date = findViewById(R.id.EditTextdate);
        airlin = findViewById(R.id.autoCompleteTextViewairline);
        insert=findViewById(R.id.insert);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locTXT = loc.getText().toString();
                String desTXT = des.getText().toString();
                String timTXT = tim.getText().toString();
                String dateTXT = date.getText().toString();
                String airlinTXT = airlin.getText().toString();



                if(locTXT.isEmpty() || desTXT.isEmpty() || timTXT.isEmpty() || dateTXT.isEmpty() || airlinTXT.isEmpty()){
                    Toast.makeText(BookFlight.this, "Empty Fields!", Toast.LENGTH_SHORT).show();

                }
                else{

                    Boolean checkinsertdata  = DB.insertflightdata(locTXT, desTXT, timTXT,dateTXT,airlinTXT);
                    if(checkinsertdata==true)
                    {
                        Toast.makeText(BookFlight.this, "Flight Booked", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(BookFlight.this, "Flight Booking Failed!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        //   calendarView = findViewById(R.id.calendarView);
        editText = findViewById(R.id.EditTextdate);
        iv = findViewById(R.id.imageView21);

        //calender
        final Calendar c = Calendar.getInstance();

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g = new DatePickerDialog(BookFlight.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String d= i2+"/"+(i1+1)+"/"+i;
                        editText.setText(d);

                    }
                },y,m,d);
                g.show();
            }
        });

        //select location drop down
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewloc);
        actv.setThreshold(1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, location);
        actv.setAdapter(adapter1);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                 actv.showDropDown();
            }
        });

        //select destination drop down
        actv1 =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewdes);
        actv1.setThreshold(1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, destination);
        actv1.setAdapter(adapter2);
        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                actv1.showDropDown();
            }
        });

        //select time drop down
        actv2 = findViewById(R.id.autoCompleteTextViewtime);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, time);
        actv2.setAdapter(adapter3);
        actv2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });

        //select airline drop down
        actv3 = findViewById(R.id.autoCompleteTextViewairline);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, airline);
        actv3.setAdapter(adapter4);
        actv3.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });



    }
}