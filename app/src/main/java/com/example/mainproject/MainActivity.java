package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainproject.CityWeather;
import com.example.mainproject.Weather;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgDrawer =findViewById(R.id.imgDrawer);
        final DrawerLayout drawerMenu = findViewById(R.id.drawerMenu);

        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerMenu.openDrawer(Gravity.LEFT);

            }
        });

        String url = "http://api.openweathermap.org/data/2.5/weather?id=6356055&APPID=651f9663537c4c2ec6965054e5025a83";
        AsyncHttpClient client =new AsyncHttpClient();

        client.get(url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();

                CityWeather cityWeather =gson.fromJson(response.toString(),CityWeather.class);

                //Init City Name
                String cityName = cityWeather.getName();
                TextView cityId = findViewById(R.id.cityId);
                cityId.setText(cityName);

                //Init Country Name
                TextView countryId = findViewById(R.id.countryId);
                countryId.setText(cityWeather.getSys().getCountry());

                //Init Degrees
                TextView txtDegrees = findViewById(R.id.txtDegrees);
                double temp = (cityWeather.getMain().getTemp() -273.15);
                int deg = (int) Math.round(temp);
                txtDegrees.setText(String.valueOf(deg));

                //Init Weather Type
                TextView txtWeather = findViewById(R.id.txtWeather);
                ImageView imgWeather = findViewById(R.id.imgWeather);

                try {


                String result= response.getString("weather");

                    JSONArray jsonArray = new JSONArray(result);
                    JSONObject object =jsonArray.getJSONObject(0);
                    String result2 = object.getString("main");

                txtWeather.setText(result2);

                switch (result2){
                    case "Rain":{imgWeather.setBackgroundResource(R.drawable.rain_fourzero); break;}
                    case "Clear":{imgWeather.setBackgroundResource(R.drawable.clear_threetwo);break;}
                    case "Snow":{imgWeather.setBackgroundResource(R.drawable.snow_onefour);break;}
                    case "Atmosphere":{imgWeather.setBackgroundResource(R.drawable.atmospher_twotwo);break;}
                    case "Drizzle":{imgWeather.setBackgroundResource(R.drawable.drizzle_oneone);break;}
                    case "Thunderstorm":{imgWeather.setBackgroundResource(R.drawable.thunder_zero);break;}
                    default:imgWeather.setBackgroundResource(R.drawable.cloudy);

                }

                }catch (Exception e){e.printStackTrace();
                    Toast.makeText(MainActivity.this, "unable to handle recieved data", Toast.LENGTH_SHORT).show();}



            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                throwable.getMessage();
            }
        });
            }

    }

