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





package com.example.retrofit_postdata

import android.R.attr
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern
import android.R.attr.password




class MainActivity2 : AppCompatActivity() {

    private var editTextMobile: EditText? = null
    private var editTextPassword: EditText? = null
    private var buttonSubmit: Button? = null
    private var textviewResponse: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // initializing our views
        editTextMobile  = findViewById(R.id.ed_mobilenumber)
        editTextPassword = findViewById(R.id.ed_password)
        buttonSubmit = findViewById(R.id.postdatabtn)
        textviewResponse = findViewById(R.id.idTVResponse)

        // adding on click listener to our button.
        textviewResponse?.setOnClickListener(View.OnClickListener { // validating if the text field is empty or not.
            if (!isValidMobileNumber(editTextMobile?.text.toString())) {
                Toast.makeText(
                    this@MainActivity2,
                    "Please enter valid mobile number",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            if (!isValidPassword( editTextPassword?.text.toString())) {
                Toast.makeText(
                    this@MainActivity2,
                    "Please enter valid password",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            // calling a method to post the data and passing our name and job.
            postData(editTextMobile?.text.toString(), editTextPassword?.text.toString())
        })
    }
    private fun isValidMobileNumber(mobileNumber:String):Boolean{
        val pattern= Pattern.compile("(5)?[0-9]{12}")
        val matcher=pattern.matcher(mobileNumber)
        return (matcher.find()&&matcher.group().equals(mobileNumber))
    }
    private fun isValidPassword(password:String):Boolean{
        // for checking if password length
        // is between 8 and 20
        // for checking if password length
        // is between 8 and 20
        if (!(attr.password.length() >= 8
                    && attr.password.length() <= 20)
        ) {
            return false
        }

        // to check space

        // to check space
        if (attr.password.contains(" ")) {
            return false
        }
        if (true) {
            var count = 0

            // check digits from 0 to 9
            for (i in 0..9) {

                // to convert int to string
                val str1 = Integer.toString(i)
                if (attr.password.contains(str1)) {
                    count = 1
                }
            }
            if (count == 0) {
                return false
            }
        }

        // for special characters

        // for special characters
        if (!(attr.password.contains("@") || attr.password.contains("#")
                    || attr.password.contains("!") || attr.password.contains("~")
                    || attr.password.contains("$") || attr.password.contains("%")
                    || attr.password.contains("^") || attr.password.contains("&")
                    || attr.password.contains("*") || attr.password.contains("(")
                    || attr.password.contains(")") || attr.password.contains("-")
                    || attr.password.contains("+") || attr.password.contains("/")
                    || attr.password.contains(":") || attr.password.contains(".")
                    || attr.password.contains(", ") || attr.password.contains("<")
                    || attr.password.contains(">") || attr.password.contains("?")
                    || attr.password.contains("|"))
        ) {
            return false
        }

        if (true) {
            var count = 0

            // checking capital letters
            for (i in 65..90) {

                // type casting
                val c = i.toChar()
                val str1 = Character.toString(c)
                if (attr.password.contains(str1)) {
                    count = 1
                }
            }
            if (count == 0) {
                return false
            }
        }

        if (true) {
            var count = 0

            // checking small letters
            for (i in 90..122) {

                // type casting
                val c = i.toChar()
                val str1 = Character.toString(c)
                if (attr.password.contains(str1)) {
                    count = 1
                }
            }
            if (count == 0) {
                return false
            }
        }

        // if all conditions fails

        // if all conditions fails
        return true
    }
    private fun postData(mobile: String, password: String) {

        // below line is to create an instance for our retrofit api class.

       val retrofitAPI: RestApi = ServiceBuilder.buildService(RestApi::class.java)
       val data =UserInfo("Android","f6f2d0e2c2fcc9eg",
           1.0,"en",1.0,"12.23.23",125.25325,
           125.25328,"NA","SmasungG2","125.25.325",
            "125.25.325","1.0.0","DRIVER/DELIVERY_BY_SME","LOGINWITHPASSWORD","NA")

        // calling a method to create a post and passing our modal class.
        val call: Call<UserInfoResponse> = retrofitAPI.addUser(data)

        // on below line we are executing our method.
        call.enqueue(object : Callback<UserInfoResponse?> {
            override fun onResponse(call: Call<UserInfoResponse?>, response: Response<UserInfoResponse?>) {
                // this method is called when we get response from our api.
                Toast.makeText(this@MainActivity2, "Data added to API", Toast.LENGTH_SHORT).show()

                // below line is for hiding our progress bar.

                // on below line we are setting empty text
                // to our both edit text.
                editTextMobile?.setText("")
                editTextPassword?.setText("")

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: UserInfoResponse? = response.body()

                // on below line we are getting our data from modal class and adding it to our string.
                val responseString = "Response Code : ${response.code()} " +
                        "timeStamp : ${responseFromAPI?.timeStamp}" +
                        "name : ${responseFromAPI?.name}" +
                        "type : ${responseFromAPI?.type}" +
                        "status : ${responseFromAPI?.status}" +
                        "token : ${responseFromAPI?.token}"

                // below line we are setting our
                // string to our text view.
                textviewResponse!!.text = responseString
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<UserInfoResponse?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                textviewResponse!!.text = "Error found is : " + t.message
            }
        })
    }
}
