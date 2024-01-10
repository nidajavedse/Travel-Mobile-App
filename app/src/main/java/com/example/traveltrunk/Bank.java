package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bank extends AppCompatActivity implements View.OnClickListener{

    EditText cardNo, name, cvv,date,amount;
    CreditCardDataBase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);


        //update button
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);

        /***** Bank *****/

        cardNo = (EditText) findViewById(R.id.holderName);
        name = (EditText) findViewById(R.id.EditTextcardNumber);
        cvv = (EditText) findViewById(R.id.EditTextcvv);
        date = (EditText) findViewById(R.id.EditTextexpiryDate);
        amount = (EditText) findViewById(R.id.EditTextAmount);
        DB = new CreditCardDataBase(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button2:
                String card = cardNo.getText().toString();
                String Name = name.getText().toString();
                String CVV = cvv.getText().toString();
                String Date = date.getText().toString();
                String Amount = amount.getText().toString();
                if(card.equals("")||Name.equals("")||CVV.equals("")||Date.equals("")||Amount.equals(""))
                    Toast.makeText(Bank.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkcard = DB.checkcardNo(card);
                    if(checkcard==false){
                        Boolean insert = DB.insertData(card,Name,CVV,Date,Amount);
                        if(insert==true){
                            Toast.makeText(Bank.this, "added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Bank.this, "Not added", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Bank.this, "card no exists! please change card no", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
}