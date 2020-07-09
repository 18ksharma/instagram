package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    EditText tvUsername;
    EditText tvPassword;
    EditText tvConfirm;
    EditText tvEmail;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ParseUser user = new ParseUser();
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvConfirm = findViewById(R.id.tvConfirm);
        tvEmail = findViewById(R.id.tvEmail);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);


        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                // Set core properties
                user.setUsername(tvUsername.getText().toString());
                Log.i("Signup","tvpass: "+tvPassword.getText().toString());

                Log.i("Signup","tvconfirm: "+tvConfirm.getText().toString());
                String password;
                String confirm;
                password=tvPassword.getText().toString();
                confirm=tvConfirm.getText().toString();
                if(!password.equals(confirm)){
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setPassword(tvPassword.getText().toString());
                user.setEmail(tvEmail.getText().toString());
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            ParseUser currentUser = ParseUser.getCurrentUser();
                            Intent i = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Failure to signup", Toast.LENGTH_SHORT).show();
                            Log.e("Signup", "error:"+e);
                        }
                    }
                });
            }
        });
    }
}