package com.example.traveltrunk;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserProfile extends AppCompatActivity implements View.OnClickListener{

    ImageView userImage;
    Button plusButton;
    int y,m,d;
    CircleImageView image;
    final int PICK_IMAGE = 100;
    Uri imageUri;
    EditText username, fname, lname,dob,city;
    UserProfileDataBase DB;
    private static final int PICK_IMAGE_REQUEST = 99;
    private Uri imagePath;
    ImageView iv;
    private Bitmap imageToStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //image
        image = findViewById(R.id.users);
        //image add button
        ImageButton buttonPlus = (ImageButton) findViewById(R.id.add);
        buttonPlus.setOnClickListener(this);
        //update button
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        /***** profile *****/

        username = (EditText) findViewById(R.id.username);
        fname = (EditText) findViewById(R.id.editTextfname);
        lname = (EditText) findViewById(R.id.editTextlname);
        dob = (EditText) findViewById(R.id.editTextdob);
        city = (EditText) findViewById(R.id.editTextcity);
        DB = new UserProfileDataBase(this);



        //calender
        final Calendar c = Calendar.getInstance();
        iv = findViewById(R.id.imageView21);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog g = new DatePickerDialog(UserProfile.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String d= i2+"/"+(i1+1)+"/"+i;
                        dob.setText(d);

                    }
                },y,m,d);
                g.show();
            }
        });

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button:
                storeImage();
                break;
            case R.id.add:
                choseImage();

                break;
        }
    }

    private void choseImage() {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=  null){
                imagePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                image.setImageBitmap(imageToStore);
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void storeImage(){
        if(!username.getText().toString().isEmpty() && !fname.getText().toString().isEmpty()
                && !lname.getText().toString().isEmpty() && !dob.getText().toString().isEmpty()
                && !city.getText().toString().isEmpty() && image.getDrawable()!=null&& imageToStore!=null){
            DB.storeData(new ModelClass(username.getText().toString(),fname.getText().toString(),
                    lname.getText().toString(),dob.getText().toString(),city.getText().toString(),imageToStore));
            Toast.makeText(UserProfile.this, "added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserProfile.this,HomeActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(UserProfile.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
    }
}