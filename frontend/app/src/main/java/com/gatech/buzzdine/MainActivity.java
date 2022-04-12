package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button signUpButton;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Admin Credentials
        User admin = new User("CS4765", "buzzdine@admin.com", "buzz");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To Sign Up Screen
        signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(v -> nextScreen(SignUp.class));

        // To Login Screen
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> nextScreen(Login.class));
    }

    /**
     * Method that switches between activities
     * @param screen next screen
     */
    public void nextScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }

}


