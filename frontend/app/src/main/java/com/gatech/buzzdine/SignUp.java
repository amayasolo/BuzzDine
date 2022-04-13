package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
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
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        Button back_btn = findViewById(R.id.signup_back);
        signup_btn.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient();
            HttpUrl loginUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host("172.26.32.1:8001/user/register")
                    .addQueryParameter("username", String.valueOf(username))
                    .addQueryParameter("email", String.valueOf(email))
                    .addQueryParameter("password", String.valueOf(password))
                    .build();
            Request request = new Request.Builder().url(loginUrl).post(RequestBody.create("", MediaType.get("application/json; charset=utf-8"))).build();
            try {
                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
