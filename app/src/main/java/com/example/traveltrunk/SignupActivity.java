package com.example.traveltrunk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    public Button SignupButton;
    ImageButton googleBtn, twitterBtn;
    Intent intent;
    TextView logIn;
    EditText username, password, rePassword;
    SignUpDataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /*************** Google button ***************/

        googleBtn =(ImageButton) findViewById(R.id.gmail);
        googleBtn.setOnClickListener(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        /*************** Twitter button ***************/



        /*************** Login button ***************/

        SignupButton=(Button) findViewById(R.id.button);
        SignupButton.setOnClickListener(this);

        /*************** Email password ***************/

        username = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        rePassword = (EditText) findViewById(R.id.confirmPassword);
        DB = new SignUpDataBase(this);

        /*************** Log In ***************/

        logIn = (TextView) findViewById(R.id.login);
        logIn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = rePassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SignupActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.gmail:
                signIn();
                break;

            case R.id.login:
                intent= new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /*************** google button ***************/

    void signIn(){
        Intent signInIntent =  gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,requestCode,data);
        if(requestCode==1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                negativeToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        }
    }


    void negativeToSecondActivity(){
        finish();
        Intent intent = new Intent(SignupActivity.this,HomeActivity.class);
        startActivity(intent);
    }


}