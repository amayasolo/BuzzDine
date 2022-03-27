package com.gatech.buzzdine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import models.User;

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
}


