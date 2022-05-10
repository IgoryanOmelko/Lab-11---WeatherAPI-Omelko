package com.example.lab10_apiomelko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //String key = "92bcac04865341c895f195133221104";
    EditText etCity;
    EditText etKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etKey = findViewById(R.id.etKey);
        etKey.setText(String.valueOf("92bcac04865341c895f195133221104"));
        ;
    }

    public void btnQueryOnClick(View v) {
        String apikey = etKey.getText().toString();
        String city = etCity.getText().toString();
        Thread t = new Thread(() -> {
            try {
                URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apikey + "&q=" + city + "&aqi=no");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream is = con.getInputStream();
                byte[] buf = new byte[1024];
                String res = "";
                while (true) {
                    int len = is.read(buf, 0, buf.length);
                    if (len < 0) break;
                    res = res + new String(buf, 0, len);
                }
                con.disconnect();
                Log.d("json", res);
                JSONObject weather = new JSONObject(res);
                JSONObject current = weather.getJSONObject("current");
                float tempC = (float) current.getDouble("temp_c");
                float tempF = (float) current.getDouble("temp_f");
                float windspeedmph = (float) current.getDouble("wind_mph");
                float windspeedkph = (float) current.getDouble("wind_kph");
                String winddir = (String) current.getString("wind_dir");
                float cloud = (float) current.getDouble("cloud");
                JSONObject loc = weather.getJSONObject("location");
                String time = (String) loc.getString("localtime");
                JSONObject condition = current.getJSONObject("condition");
                String icon = condition.getString("icon");
                URL url2 = new URL("https:" + icon);
                HttpURLConnection con2 = (HttpURLConnection)
                        url2.openConnection();
                InputStream is2 = con2.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(is2);
                Intent i = new Intent(this, WeatherDataActivty.class);
                i.putExtra("img", bmp);
                i.putExtra("tempC", String.valueOf(tempC));
                i.putExtra("tempF", String.valueOf(tempF));
                i.putExtra("time", time);
                i.putExtra("windspeedmph", String.valueOf(windspeedmph));
                i.putExtra("windspeedkph", String.valueOf(windspeedkph));
                i.putExtra("winddir", winddir);
                i.putExtra("cloud", String.valueOf(cloud));
                startActivity(i);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        t.start();
    }
}