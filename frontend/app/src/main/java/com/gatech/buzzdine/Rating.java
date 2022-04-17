package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import models.User;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
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
        String longitude = getIntent().getStringExtra("longitude");
        String latitude = getIntent().getStringExtra("latitude");
        String filter = getIntent().getStringExtra("filter");

        save_btn.setOnClickListener(v -> {
            User currentUser = Login.getCurrentUser();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://"+getResources().getString(R.string.ip)+":8001/restaurant/updateRating?username="+currentUser.getUsername()+"&restaurantName="+getIntent().getStringExtra("restaurant")+"&rating="+ Math.round(rating.getRating())).post(RequestBody.create("", MediaType.get("application/json; charset=utf-8"))).build();
            try {
                Response response = client.newCall(request).execute();
                String result = Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, Top5.class);
            intent.putExtra("longitude", longitude);
            intent.putExtra("latitude", latitude);
            intent.putExtra("filter", filter);
            startActivity(intent);
        });
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Top5.class);
            intent.putExtra("longitude", longitude);
            intent.putExtra("latitude", latitude);
            intent.putExtra("filter", filter);
            startActivity(intent);
        });
    }
}
