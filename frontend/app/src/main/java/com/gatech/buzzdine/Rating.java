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

public class Rating extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        RatingBar rating = findViewById(R.id.rating_bar);
        Button save_btn = findViewById(R.id.rate_save);
        Button back_btn = findViewById(R.id.rate_back);

        save_btn.setOnClickListener(v -> {
            User currentUser = Login.getCurrentUser();
            OkHttpClient client = new OkHttpClient();
            HttpUrl loginUrl = new HttpUrl.Builder()
                    .scheme("http")
                    .host("172.26.32.1:8001/restaurant/updateRating")
                    .addQueryParameter("username", currentUser.getUsername())
                    .addQueryParameter("restaurantName", getIntent().getStringExtra("restaurant"))
                    .addQueryParameter("rating", String.valueOf(Math.round(Float.parseFloat(String.valueOf(rating)))))
                    .build();
            Request request = new Request.Builder().url(loginUrl).build();
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
