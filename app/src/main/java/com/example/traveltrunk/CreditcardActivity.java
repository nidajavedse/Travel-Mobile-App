package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreditcardActivity extends AppCompatActivity implements View.OnClickListener{

    EditText cardNo, name, cvv,date,amount;
    ImageView bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);


        //update button
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

        /***** Bank *****/
        bank=(ImageView) findViewById(R.id.imageView20);
        bank.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageView20:
                Intent intent= new Intent(CreditcardActivity.this, Bank.class);
                startActivity(intent);
                break;
        }
    }
}