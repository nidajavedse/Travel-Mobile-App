package com.example.traveltrunk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String PREFS_NAME = "myPrefsFile";
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    public Button loginButton;
    ImageButton googleBtn, twitterBtn;
    Intent intent;
    EditText username, password, rePassword;
    TextView signUp;
    SignUpDataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*************** Google button ***************/

        googleBtn =(ImageButton) findViewById(R.id.gmail);
        googleBtn.setOnClickListener(this);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);


        /*************** Login button ***************/

        loginButton=(Button) findViewById(R.id.button);
        loginButton.setOnClickListener(this);

        /*************** Email password ***************/

        username = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        DB = new SignUpDataBase(this);

        /*************** Sign up ***************/

        signUp = (TextView) findViewById(R.id.SignUp);
        signUp.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("hasLoggedIn",true);
                editor.commit();

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
               else{
                    if(user.equals("")||pass.equals(""))
                        Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if(checkuserpass==true){
                            Toast.makeText(LoginActivity.this, "Log in successful", Toast.LENGTH_SHORT).show();
                            Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    }

                break;
            case R.id.gmail:
                signIn();
                break;

            case R.id.SignUp:
                intent= new Intent(LoginActivity.this,SignupActivity.class);
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
        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
    }

}