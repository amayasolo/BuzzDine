package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import models.User;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Spinner place = findViewById(R.id.setting_place);
        Spinner least_place = findViewById(R.id.setting_least_place);
        Spinner cuisine = findViewById(R.id.setting_cuisine);
        Spinner least_cuisine = findViewById(R.id.setting_least_cuisine);
        EditText friends = findViewById(R.id.setting_friend);
        Button save_btn = findViewById(R.id.setting_save);
        Button back_btn = findViewById(R.id.setting_back);

        //TODO get data

        save_btn.setOnClickListener(v -> {
            User currentUser = Login.getCurrentUser();
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBodyPost = new FormBody.Builder()
                    .add("username", currentUser.getUsername())
                    .add("favourite_dining_place", String.valueOf(place))
                    .add("least_favourite_dining_place", String.valueOf(least_place))
                    .add("favourite_cuisine", String.valueOf(cuisine))
                    .add("least_favourite_cuisine", String.valueOf(least_cuisine))
                    .add("friends", String.valueOf(friends))
                    .build();
            Request request = new Request.Builder().url("http://172.26.32.1:8001/user/updateSetting").post(requestBodyPost).build();
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
