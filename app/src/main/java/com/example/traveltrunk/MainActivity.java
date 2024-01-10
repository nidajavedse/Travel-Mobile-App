package com.example.traveltrunk;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
                        if(hasLoggedIn){
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                },3000);
            }
        }