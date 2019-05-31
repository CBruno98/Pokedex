package com.example.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mRegisterUserName, mRegisterPassword, mRegisterConfPassword;
    Button mDoRegisterButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        db = new DatabaseHelper(this);
        mRegisterUserName = (EditText) findViewById(R.id.user_reg);
        mRegisterPassword= (EditText) findViewById(R.id.password_reg);
        mRegisterConfPassword = (EditText) findViewById(R.id.confirm_password_reg);
        mDoRegisterButton = (Button) findViewById(R.id.do_registration_button);

        mDoRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mRegisterUserName.getText().toString().trim();
                String pwd = mRegisterPassword.getText().toString().trim();
                String cnf_pwd = mRegisterConfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"User Already Registered",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
