package com.gatech.buzzdine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

import models.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static User currentUser;
    private Button signUpButton;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Admin Credentials
        User admin = new User("CS", "4765", "buzzdine@admin.com", "buzz");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send();

        // To Sign Up Screen
        signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextScreen(SignUp.class);
            }
        });

        // To Login Screen
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextScreen(Login.class);
            }
        });
    }

    /**
     * Method that switches between activities
     * @param screen next screen
     */
    public void nextScreen(Class screen) {
        Intent intent = new Intent(this, screen);
        startActivity(intent);
    }

    /**
     * Gets current user
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets current user
     * @param newUser user to set to
     */
    public static void setCurrentUser(User newUser) {
        currentUser = newUser;
    }

    private void send() {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://172.26.32.1:8001/restaurant/getAll").build();
            try {
                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


