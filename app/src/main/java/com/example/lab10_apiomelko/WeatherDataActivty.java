package com.example.lab10_apiomelko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherDataActivty extends AppCompatActivity {
    TextView tvTempCv, tvTempFv, tvTimev, tvWindMv, tvWindKv, tvWindDv, tvCloudv;
    ImageView imgv;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_data_activty);
        btnBack = findViewById(R.id.btnBack);
        tvTempCv = findViewById(R.id.tvTempCv);
        tvTempFv = findViewById(R.id.tvTempFv);
        tvTimev = findViewById(R.id.tvTimev);
        tvWindMv = findViewById(R.id.tvWindMv);
        tvWindKv = findViewById(R.id.tvWindKv);
        tvWindDv = findViewById(R.id.tvWindDv);
        tvCloudv = findViewById(R.id.tvCloudv);
        imgv = findViewById(R.id.imgv);
        Intent i = getIntent();
        tvTempCv.setText((i.getStringExtra("tempC")));
        tvTempFv.setText((i.getStringExtra("tempF")));
        tvTimev.setText((i.getStringExtra("time")));
        tvWindMv.setText((i.getStringExtra("windspeedmph")));
        tvWindKv.setText((i.getStringExtra("windspeedkph")));
        tvWindDv.setText((i.getStringExtra("winddir")));
        tvCloudv.setText((i.getStringExtra("cloud")));;
        Bitmap bpm = (Bitmap)i.getParcelableExtra("img");
        imgv.setImageBitmap(bpm);
    }
    public void btnBackOnClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}