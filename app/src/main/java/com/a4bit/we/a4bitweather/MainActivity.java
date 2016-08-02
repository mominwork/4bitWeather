package com.a4bit.we.a4bitweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //String poetUrl = "http://nerdcastlebd.com/_old-site/web_service/banglapoems/index.php/poets/all/format/json";

    String url = "http://api.openweathermap.org/data/2.5/weather?q=Dhaka,bd&appid=0a3d6f75ff8e0621c702d3bace78059c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPoetsName();

    }

    private void getPoetsName() {

        final double KELVIN = 273.15;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = response.getJSONObject("main");
                    String temp = jsonObject.getString("temp");
                    double value = Double.parseDouble(temp);
                    double tempInCelcius = value - KELVIN;
                    tempInCelcius=Math.floor(tempInCelcius*10) / 10;
                    String strTempInCelcius = String.valueOf(tempInCelcius);

                    Toast.makeText(MainActivity.this, strTempInCelcius, Toast.LENGTH_SHORT).show();

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
