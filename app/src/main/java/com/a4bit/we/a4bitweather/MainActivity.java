package com.a4bit.we.a4bitweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    //String poetUrl = "http://nerdcastlebd.com/_old-site/web_service/banglapoems/index.php/poets/all/format/json";


    CurrentWeatherURL currentWeatherURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HashMap<String, String> listOfCities = new HashMap<String, String>();

        listOfCities.put("Dhaka", "1185241");
        listOfCities.put("Faridpur", "1203344");
        listOfCities.put("Tungi", "1185098");
        listOfCities.put("Tungipara", "1185920");
        listOfCities.put("Jamalpur", "1185106");
        listOfCities.put("Kishorganj", "1337249");
        listOfCities.put("Madaripur", "1337245");
        listOfCities.put("Manikganj", "1348441");
        listOfCities.put("Mymensingh", "1185162");
        listOfCities.put("Narayanganj", "1185155");

        listOfCities.put("Narsingdi", "1185117");
        listOfCities.put("Netrakona", "1185116");
        listOfCities.put("Sripur", "1187197");
        listOfCities.put("Tangail", "1336144");
        listOfCities.put("Bandarban", "1185270");
        listOfCities.put("Chandpur", "1207339");
        listOfCities.put("Chittagong", "1337200");
        listOfCities.put("Comilla", ":1185186");
        listOfCities.put("Cox%27sbazaar", "1336134");
        listOfCities.put("Feni", "1185224");

        listOfCities.put("Khagrachhari", "1185252");
        listOfCities.put("Lakshmipur", "1196292");
        listOfCities.put("Bogra", "1337233");
        listOfCities.put("Nawabganj", "1337240");
        listOfCities.put("Joypurhat", "1185206");
        listOfCities.put("Pabna", "1336143");
        listOfCities.put("Rajshahi", "1185128");
        listOfCities.put("Sirajganj", "1185115");
        listOfCities.put("Jessore", "1336140");
        listOfCities.put("Khulna", "1336135");

        listOfCities.put("Kushtia", "1185191");
        listOfCities.put("Narail", "1185293");
        listOfCities.put("Satkhira", "1185111");
        listOfCities.put("Barisal", "1336137");
        listOfCities.put("Bhola", "1336136");
        listOfCities.put("Pirojpur", "1185138");
        listOfCities.put("Habiganj", "1185209");
        listOfCities.put("Maulvibazar", "1185166");
        listOfCities.put("Chhatak", "1185254");
        listOfCities.put("Sylhet", "1185099");

        listOfCities.put("Dinajpur", "1203891");
        listOfCities.put("Gaibandha", "7921384");
        listOfCities.put("Lalmanirhat", "1185181");
        listOfCities.put("Panchagarh", "1185141");
        listOfCities.put("Rangpur", "1185188");
        listOfCities.put("Thakurgaon", "1185092");


        String searchInput = "Dhaka";



        if (listOfCities.containsKey(searchInput)) {

            String correspondingValueOfKey = listOfCities.get(searchInput);

            currentWeatherURL = new CurrentWeatherURL(correspondingValueOfKey, "metric");

            getCurrentWeatherInfo(currentWeatherURL.getUrl());

            Toast.makeText(MainActivity.this, correspondingValueOfKey, Toast.LENGTH_SHORT).show();


        } else {

            Toast.makeText(MainActivity.this, "Key not matched with ID", Toast.LENGTH_LONG).show();
        }

    }



    private void getCurrentWeatherInfo(String url) {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {



                    /*JSONObject cityNameObject = response.getJSONObject("name");
                    //cityNameObject.getString("name");
                    //String cityName = cityNameObject.toString();
                    //cityNameTV.setText(cityName);


                    Toast.makeText(MainActivity.this, "City Name: "+cityNameObject.getString("name"), Toast.LENGTH_SHORT).show();*/


                    JSONObject mainObject = response.getJSONObject("main");
                    String temp = mainObject.getString("temp");
                    double value = Double.parseDouble(temp);
                    value = Math.floor(value * 10) / 10;
                    String currentTempInCelsius = String.valueOf(value);

                    //currentTempTV.setText(currentTempInCelsius+"ºC");

                    Toast.makeText(MainActivity.this, "Current Temperature: " + currentTempInCelsius, Toast.LENGTH_SHORT).show();


                    int pressure = mainObject.getInt("pressure");
                    //pressureTV.setText(String.valueOf(pressure)+"hPa");

                    //Toast.makeText(MainActivity.this, "Pressure: "+pressure, Toast.LENGTH_SHORT).show();

                    int humidity = mainObject.getInt("humidity");
                    //humidityTV.setText(String.valueOf(humidity)+"%");

                    //Toast.makeText(MainActivity.this, "Humidity: "+humidity, Toast.LENGTH_SHORT).show();


                    int tempMin = mainObject.getInt("temp_min");
                    //temperatureMinTV.setText(String.valueOf(tempMin)+"ºC");

                    //Toast.makeText(MainActivity.this, "MinTemp: "+tempMin, Toast.LENGTH_SHORT).show();

                    int tempMax = mainObject.getInt("temp_max");
                    //temperatureMaxTV.setText(String.valueOf(tempMax)+"ºC");

                    //Toast.makeText(MainActivity.this, "MaxTemp: "+tempMax, Toast.LENGTH_SHORT).show();


                    JSONObject windObject = response.getJSONObject("wind");
                    int windSpeed = windObject.getInt("speed");
                    //windSpeedTV.setText(String.valueOf(windSpeed)+"km/h");


                    int windAngle = windObject.getInt("deg");
                    //windAngleTV.setText(String.valueOf(windAngle)+"º");


                    JSONObject sysObject = response.getJSONObject("sys");
                    String countryName = sysObject.getString("country");
                    //countryNameTV.setText(countryName);

                    //Toast.makeText(MainActivity.this, "Country ID: "+countryName, Toast.LENGTH_SHORT).show();

                    long sunriseUnixTime = sysObject.getLong("sunrise");

                    Date date = new Date(sunriseUnixTime * 1000L); // *1000 is to convert seconds to milliseconds
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a"); // the format of your date/time
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+6")); // give a timezone reference for formating
                    String formattedDate = sdf.format(date);

                    //sunriseTimeTV.setText(formattedDate);


                    Toast.makeText(MainActivity.this, "Sunrise Time: " + formattedDate, Toast.LENGTH_LONG).show();


                    long sunsetUnixTime = sysObject.getLong("sunset");

                    Date sunsetdate = new Date(sunsetUnixTime * 1000L); // *1000 is to convert seconds to milliseconds
                    SimpleDateFormat sunsetsdf = new SimpleDateFormat("h:mm a"); // the format of your date/time
                    sunsetsdf.setTimeZone(TimeZone.getTimeZone("GMT+6")); // give a timezone reference for formating
                    String formattedSunsetDate = sunsetsdf.format(sunsetdate);

                    //sunsetTimeTV.setText(formattedSunsetDate);


                    Toast.makeText(MainActivity.this, "Sunset Time: " + formattedSunsetDate, Toast.LENGTH_LONG).show();


                    JSONArray weatherArray = response.getJSONArray("weather");

                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    String weatherCondition = weatherObject.getString("main");
                    //weatherConditionTV.setText(weatherCondition);

                    Toast.makeText(MainActivity.this, weatherCondition, Toast.LENGTH_SHORT).show();


                    String weatherDescription = weatherObject.getString("description");
                    //weatherDescriptionTV.setText(weatherDescription);

                    Toast.makeText(MainActivity.this, weatherDescription, Toast.LENGTH_SHORT).show();

                    String weatehrIcon = weatherObject.getString("icon");
                    String iconURL = "http://openweathermap.org/img/w/"+weatehrIcon+".png";




                    Toast.makeText(MainActivity.this, weatherDescription, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {

                }

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }
}
