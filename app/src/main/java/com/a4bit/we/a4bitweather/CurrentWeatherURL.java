package com.a4bit.we.a4bitweather;

/**
 * Created by home on 8/3/2016.
 */
public class CurrentWeatherURL {

    String correspondingCityID;
    String unit;
    String url;

    public CurrentWeatherURL(String correspondingCityID, String unit) {
        this.correspondingCityID = correspondingCityID;
        this.unit = unit;
        url = "http://api.openweathermap.org/data/2.5/weather?id="+correspondingCityID+"&units="+unit+"&appid=0a3d6f75ff8e0621c702d3bace78059c";
    }

    public String getUrl() {
        return url;
    }
}
