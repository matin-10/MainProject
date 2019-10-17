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
        Button btnSearch =findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "this function is disable!", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imgDrawer =findViewById(R.id.imgDrawer);
        final DrawerLayout drawerMenu = findViewById(R.id.drawerMenu);

        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerMenu.openDrawer(Gravity.LEFT);

            }
        });

        Findc g =new Findc();
        String url =g.url1 = "http://api.openweathermap.org/data/2.5/weather?id=112931&APPID=651f9663537c4c2ec6965054e5025a83";
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

        //forecast Weather
        AsyncHttpClient clientforecast =new AsyncHttpClient();
        String forecastLink = "http://api.openweathermap.org/data/2.5/forecast?id=112931&APPID=651f9663537c4c2ec6965054e5025a83";

        clientforecast.get(forecastLink,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                //First day Forecast
                TextView txtDegFirst = findViewById(R.id.txtDegFirst);
                TextView txtWeatherFirst = findViewById(R.id.txtWeatherFirst);
                ImageView imgWeatherFirst =findViewById(R.id.imgWeatherFirst);
                try {


                    String result = response.getString("list");
                    JSONArray array = new JSONArray(result);
                    JSONObject object = array.getJSONObject(1);

                    String result1 = object.getString("main");
                    JSONObject object1 = new JSONObject(result1);

                    String result2 = object1.getString("temp");
                    double tempF = Double.valueOf(result2);
                    tempF-=273.15;
                    int degF=(int) Math.round(tempF);
                    txtDegFirst.setText(String.valueOf(degF));

                    String resultWeather =object.getString("weather");
                    JSONArray arrayWeatherF =new JSONArray(resultWeather);
                    JSONObject objectWeatherF = arrayWeatherF.getJSONObject(0);

                    String resultWeatherF =objectWeatherF.getString("main");
                    txtWeatherFirst.setText(resultWeatherF);


                    switch (resultWeatherF){
                        case "Rain":{imgWeatherFirst.setBackgroundResource(R.drawable.rain_fourzero); break;}
                        case "Clear":{imgWeatherFirst.setBackgroundResource(R.drawable.clear_threetwo);break;}
                        case "Snow":{imgWeatherFirst.setBackgroundResource(R.drawable.snow_onefour);break;}
                        case "Atmosphere":{imgWeatherFirst.setBackgroundResource(R.drawable.atmospher_twotwo);break;}
                        case "Drizzle":{imgWeatherFirst.setBackgroundResource(R.drawable.drizzle_oneone);break;}
                        case "Thunderstorm":{imgWeatherFirst.setBackgroundResource(R.drawable.thunder_zero);break;}
                        default:imgWeatherFirst.setBackgroundResource(R.drawable.cloudy);
                    }
                    // Second Day Forecast
                    TextView txtDegSecond = findViewById(R.id.txtDegSecond);
                    TextView txtWeatherSecond = findViewById(R.id.txtWeatherSecond);
                    ImageView imgWeatherSecond= findViewById(R.id.imgWeatherSecond);

                    JSONObject objectS = array.getJSONObject(2);

                    String result1S = objectS.getString("main");
                    JSONObject object1S = new JSONObject(result1S);

                    String result2S = object1S.getString("temp");
                    double tempS = Double.valueOf(result2S);
                    tempS-=273.15;
                    int degS=(int) Math.round(tempS);
                    txtDegSecond.setText(String.valueOf(degS));

                    String resultWeatherS =objectS.getString("weather");
                    JSONArray arrayWeatherS =new JSONArray(resultWeatherS);
                    JSONObject objectWeatherS = arrayWeatherS.getJSONObject(0);

                    String resultWeatherSe =objectWeatherS.getString("main");
                    txtWeatherSecond.setText(resultWeatherSe);


                    switch (resultWeatherSe){
                        case "Rain":{imgWeatherSecond.setBackgroundResource(R.drawable.rain_fourzero); break;}
                        case "Clear":{imgWeatherSecond.setBackgroundResource(R.drawable.clear_threetwo);break;}
                        case "Snow":{imgWeatherSecond.setBackgroundResource(R.drawable.snow_onefour);break;}
                        case "Atmosphere":{imgWeatherSecond.setBackgroundResource(R.drawable.atmospher_twotwo);break;}
                        case "Drizzle":{imgWeatherSecond.setBackgroundResource(R.drawable.drizzle_oneone);break;}
                        case "Thunderstorm":{imgWeatherSecond.setBackgroundResource(R.drawable.thunder_zero);break;}
                        default:imgWeatherSecond.setBackgroundResource(R.drawable.cloudy);
                    }

                    //Third Day Forecast

                    TextView txtDegThird = findViewById(R.id.txtDegThird);
                    TextView txtWeatherThird = findViewById(R.id.txtWeatherThird);
                    ImageView imgWeatherThird= findViewById(R.id.imgWeatherThird);

                    JSONObject objectT = array.getJSONObject(3);

                    String result1T = objectT.getString("main");
                    JSONObject object1T = new JSONObject(result1T);

                    String result2T = object1T.getString("temp");
                    double tempT = Double.valueOf(result2T);
                    tempT-=273.15;
                    int degT=(int) Math.round(tempT);
                    txtDegThird.setText(String.valueOf(degT));

                    String resultWeatherT =objectT.getString("weather");
                    JSONArray arrayWeatherT =new JSONArray(resultWeatherT);
                    JSONObject objectWeatherT = arrayWeatherT.getJSONObject(0);

                    String resultWeatherTh =objectWeatherT.getString("main");
                    txtWeatherThird.setText(resultWeatherTh);


                    switch (resultWeatherTh){
                        case "Rain":{imgWeatherThird.setBackgroundResource(R.drawable.rain_fourzero); break;}
                        case "Clear":{imgWeatherThird.setBackgroundResource(R.drawable.clear_threetwo);break;}
                        case "Snow":{imgWeatherThird.setBackgroundResource(R.drawable.snow_onefour);break;}
                        case "Atmosphere":{imgWeatherThird.setBackgroundResource(R.drawable.atmospher_twotwo);break;}
                        case "Drizzle":{imgWeatherThird.setBackgroundResource(R.drawable.drizzle_oneone);break;}
                        case "Thunderstorm":{imgWeatherThird.setBackgroundResource(R.drawable.thunder_zero);break;}
                        default:imgWeatherThird.setBackgroundResource(R.drawable.cloudy);
                    }

                    //Fourth Day Forecast

                    TextView txtDegFourth = findViewById(R.id.txtDegFourth);
                    TextView txtWeatherFourth = findViewById(R.id.txtWeatherFourth);
                    ImageView imgWeatherFourth= findViewById(R.id.imgWeatherFourth);

                    JSONObject objectF = array.getJSONObject(4);

                    String result1F = objectF.getString("main");
                    JSONObject object1F = new JSONObject(result1F);

                    String result2F = object1F.getString("temp");
                    double tempFo = Double.valueOf(result2F);
                    tempFo-=273.15;
                    int degFo=(int) Math.round(tempFo);
                    txtDegFourth.setText(String.valueOf(degFo));

                    String resultWeatherFo =objectF.getString("weather");
                    JSONArray arrayWeatherFo =new JSONArray(resultWeatherFo);
                    JSONObject objectWeatherFo = arrayWeatherFo.getJSONObject(0);

                    String resultWeatherFou =objectWeatherFo.getString("main");
                    txtWeatherFourth.setText(resultWeatherFou);


                    switch (resultWeatherFou){
                        case "Rain":{imgWeatherFourth.setBackgroundResource(R.drawable.rain_fourzero); break;}
                        case "Clear":{imgWeatherFourth.setBackgroundResource(R.drawable.clear_threetwo);break;}
                        case "Snow":{imgWeatherFourth.setBackgroundResource(R.drawable.snow_onefour);break;}
                        case "Atmosphere":{imgWeatherFourth.setBackgroundResource(R.drawable.atmospher_twotwo);break;}
                        case "Drizzle":{imgWeatherFourth.setBackgroundResource(R.drawable.drizzle_oneone);break;}
                        case "Thunderstorm":{imgWeatherFourth.setBackgroundResource(R.drawable.thunder_zero);break;}
                        default:imgWeatherFourth.setBackgroundResource(R.drawable.cloudy);
                    }


                }catch (Exception e){e.printStackTrace();}
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                throwable.getMessage();
            }
        });

            }

    }

