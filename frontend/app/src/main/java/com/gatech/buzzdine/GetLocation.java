package com.gatech.buzzdine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.gatech.buzzdine.entity.FilterType;

public class GetLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button location_back = findViewById(R.id.location_back);
        Button location_btn = findViewById(R.id.location_btn);
        ImageView setting_btn = findViewById(R.id.setting_btn);
        EditText longitude = findViewById(R.id.longitude);
        EditText latitude = findViewById(R.id.latitude);
        Spinner filter = findViewById(R.id.filterType);
        location_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Top5.class);
            intent.putExtra("longitude", longitude.getText().toString());
            intent.putExtra("latitude", latitude.getText().toString());
            intent.putExtra("filter", String.valueOf(FilterType.getFilter(filter.getSelectedItem().toString()).getIndex()));
            startActivity(intent);
        });
        location_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
        setting_btn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        });
    }

}
