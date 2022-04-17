package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gatech.buzzdine.GetLocation;
import com.gatech.buzzdine.Login;
import com.gatech.buzzdine.R;
import com.gatech.buzzdine.Rating;
import com.gatech.buzzdine.entity.FilterType;
import com.gatech.buzzdine.entity.RestaurantInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Top5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5);

        Button top5_back = findViewById(R.id.top5_back);
        Button top1_btn = findViewById(R.id.top1_btn);
        Button top2_btn = findViewById(R.id.top2_btn);
        Button top3_btn = findViewById(R.id.top3_btn);
        Button top4_btn = findViewById(R.id.top4_btn);
        Button top5_btn = findViewById(R.id.top5_btn);
        String longitude = getIntent().getStringExtra("longitude");
        String latitude = getIntent().getStringExtra("latitude");
        String filter = getIntent().getStringExtra("filter");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://"+getResources().getString(R.string.ip)+":8001/restaurant/getRecommend?username="
                + Login.getCurrentUser().getUsername() +"&longitude="+longitude+"&latitude="+latitude+"&filterType="+filter)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = Objects.requireNonNull(response.body()).string();
            JsonArray array = JsonParser.parseString(result).getAsJsonArray();
            Gson gson = new Gson();
            String name1 = gson.fromJson(array.get(0).toString(), RestaurantInfo.class).getName();
            top1_btn.setText(name1);
            top1_btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Rating.class);
                intent.putExtra("restaurant", name1);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("filter", filter);
                startActivity(intent);
            });
            String name2 = gson.fromJson(array.get(1).toString(), RestaurantInfo.class).getName();
            top2_btn.setText(name2);
            top2_btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Rating.class);
                intent.putExtra("restaurant", name2);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("filter", filter);
                startActivity(intent);
            });
            String name3 = gson.fromJson(array.get(2).toString(), RestaurantInfo.class).getName();
            top3_btn.setText(name3);
            top3_btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Rating.class);
                intent.putExtra("restaurant", name3);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("filter", filter);
                startActivity(intent);
            });
            String name4 = gson.fromJson(array.get(3).toString(), RestaurantInfo.class).getName();
            top4_btn.setText(name4);
            top4_btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Rating.class);
                intent.putExtra("restaurant", name4);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("filter", filter);
                startActivity(intent);
            });
            String name5 = gson.fromJson(array.get(4).toString(), RestaurantInfo.class).getName();
            top5_btn.setText(name5);
            top5_btn.setOnClickListener(v -> {
                Intent intent = new Intent(this, Rating.class);
                intent.putExtra("restaurant", name5);
                intent.putExtra("longitude", longitude);
                intent.putExtra("latitude", latitude);
                intent.putExtra("filter", filter);
                startActivity(intent);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        top5_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, GetLocation.class);
            startActivity(intent);
        });
    }
}
