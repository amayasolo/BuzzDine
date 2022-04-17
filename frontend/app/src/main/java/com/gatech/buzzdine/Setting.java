package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gatech.buzzdine.entity.Restaurant;
import com.gatech.buzzdine.entity.UserSetting;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import models.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner place = findViewById(R.id.setting_place);
        Spinner least_place = findViewById(R.id.setting_least_place);
        Spinner cuisine = findViewById(R.id.setting_cuisine);
        Spinner least_cuisine = findViewById(R.id.setting_least_cuisine);
        EditText friends = findViewById(R.id.setting_friend);
        Button save_btn = findViewById(R.id.setting_save);
        Button back_btn = findViewById(R.id.setting_back);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://"+getResources().getString(R.string.ip) + ":8001/user/getSetting?username="+Login.getCurrentUser().getUsername())
                .build();
        try {
            Response response = client.newCall(request).execute();
            String result = Objects.requireNonNull(response.body()).string();
            UserSetting setting = new Gson().fromJson(result, UserSetting.class);
            if (setting != null && setting.getFavourite_dining_place() != null){
                place.setSelection(Restaurant.getRestaurant(setting.getFavourite_dining_place()).getIndex());
            }
            if (setting != null && setting.getLeast_favourite_dining_place() != null){
                place.setSelection(Restaurant.getRestaurant(setting.getLeast_favourite_dining_place()).getIndex());
            }
            if (setting != null && setting.getFavourite_cuisine() != null){
                place.setSelection(Restaurant.getRestaurant(setting.getFavourite_cuisine()).getIndex());
            }
            if (setting != null && setting.getLeast_favourite_cuisine() != null){
                place.setSelection(Restaurant.getRestaurant(setting.getLeast_favourite_cuisine()).getIndex());
            }
            if (setting != null && setting.getFriends() != null){
                place.setSelection(Restaurant.getRestaurant(setting.getFriends()).getIndex());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        save_btn.setOnClickListener(v -> {
            User currentUser = Login.getCurrentUser();
            Request request1 = new Request.Builder()
                    .url("http://"+getResources().getString(R.string.ip)+":8001/user/updateSetting?username="+ currentUser.getUsername()+"&favourite_dining_place="+place.getSelectedItem().toString()+"&least_favourite_dining_place="+least_place.getSelectedItem().toString()+"&favourite_cuisine="+cuisine.getSelectedItem().toString()+"&least_favourite_cuisine="+least_cuisine.getSelectedItem().toString()+"&friends="+friends.getText().toString())
                    .post(RequestBody.create("", MediaType.get("application/json; charset=utf-8"))).build();
            try {
                Response response = client.newCall(request1).execute();
                String result = Objects.requireNonNull(response.body()).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, GetLocation.class);
            startActivity(intent);
        });
        back_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GetLocation.class);
            startActivity(intent);
        });
    }
}
