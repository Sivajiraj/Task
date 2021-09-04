package com.ideallabs.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mobileNumber,password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
    }

    private void setData() {
         mobileNumber= (EditText) findViewById(R.id.mobileNumberET);
        password = (EditText) findViewById(R.id.userPasswordET);
        signUp = (Button) findViewById(R.id.signupBTN);

        String mobilePattern = "[5]{1}[0-9]{11}";
        String passwordPattern ="^.*(?=.{8,})(?=.*\\d)(?=.*[a-zA-Z])|(?=.{8,})(?=.*\\d)(?=.*[!@#$%^&])|(?=.{8,})(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$";



        signUp.setOnClickListener(view -> {

            String mobile = mobileNumber.getText().toString();
            String pass = password.getText().toString();

            //for mobile validation
            if (mobile != null){
                if (mobile.length()==12 && mobile.matches(mobilePattern)){
                    Toast.makeText(MainActivity.this, "mobile is correct", Toast.LENGTH_SHORT).show();
                }else{
                    mobileNumber.setError("enter valid phone number");
                }
            }else{
                mobileNumber.setError("Please enter a email");
            }

            //for Password validation
            if (pass != null){
                if (pass.length() >= 8 && pass.matches(passwordPattern)){
                    Toast.makeText(MainActivity.this, "Password is correct", Toast.LENGTH_SHORT).show();
                }else{
                    password.setError("Password is not in alphaNumeric");
                }
            }else{
                password.setError("please enter a password");
            }

        });
    }
}