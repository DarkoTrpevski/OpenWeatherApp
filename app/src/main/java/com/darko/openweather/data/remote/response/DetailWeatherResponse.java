package com.darko.openweather.data.remote.response;

import com.darko.openweather.data.model.City;
import com.darko.openweather.data.model.entity.WeatherData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailWeatherResponse {

    private String cod;
    private double message;
    private int cnt;
    @SerializedName("list")
    private List<WeatherData> weatherDataList = null;
    private City city;

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public City getCity() {
        return city;
    }

    public boolean isError() {
        return !cod.equals("200");
    }
}