package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gatech.buzzdine.MainActivity;
import com.gatech.buzzdine.R;
import com.gatech.buzzdine.SignUp;

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
        Button back_btn = findViewById(R.id.login_back);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        login_btn.setOnClickListener(v -> {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://"+getResources().getString(R.string.ip)+":8001/user/login?username="+username.getText().toString()+"&password="+password.getText().toString()).build();
            try {
                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setCurrentUser(new User(username.getText().toString(), "", password.getText().toString()));
            Intent intent = new Intent(this, GetLocation.class);
            startActivity(intent);
        });
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
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
