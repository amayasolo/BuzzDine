package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import models.User;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = findViewById(R.id.login_btn);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login_btn.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient();
            HttpUrl loginUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host("172.26.32.1:8001/user/login")
                    .addQueryParameter("username", String.valueOf(username))
                    .addQueryParameter("password", String.valueOf(password))
                    .build();
            Request request = new Request.Builder().url(loginUrl).build();
            try {
                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setCurrentUser(new User(String.valueOf(username), "", String.valueOf(password)));
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
    }

    /**
     * Gets current user
     * @return current user
     */
    public static User getCurrentUser() {
        return user;
    }

    /**
     * Sets current user
     * @param newUser user to set to
     */
    public static void setCurrentUser(User newUser) {
        user = newUser;
    }
}
