package com.example.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mLoginUsername, mLoginPassword;
    Button mButtonLogin, mButtonRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mLoginUsername = (EditText) findViewById(R.id.user_login);
        mLoginPassword = (EditText) findViewById(R.id.password_login);
        mButtonLogin = (Button) findViewById(R.id.login_button);
        mButtonRegister = (Button) findViewById(R.id.register_button);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mLoginUsername.getText().toString().trim();
                String pwd = mLoginPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if(res == true)
                {
                    //Intent HomePage = new Intent(LoginActivity.this,PokemonesList.class);
                    //startActivity(HomePage);
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToRegisterActivity(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
