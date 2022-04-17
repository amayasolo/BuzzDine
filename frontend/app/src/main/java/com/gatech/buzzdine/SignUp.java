package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signup_btn = findViewById(R.id.signup_btn);
        username = findViewById(R.id.signup_username);
        System.out.println(username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        Button back_btn = findViewById(R.id.signup_back);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        signup_btn.setOnClickListener(v -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://"+getResources().getString(R.string.ip)+":8001/user/register?username="+username.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()).post(RequestBody.create("", MediaType.get("application/json; charset=utf-8"))).build();

                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
