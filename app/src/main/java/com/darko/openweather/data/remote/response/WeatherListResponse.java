package com.darko.openweather.data.remote.response;

import com.darko.openweather.data.model.entity.WeatherData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherListResponse {
    @SerializedName("cnt")
    private int count;
    @SerializedName("list")
    private List<WeatherData> weatherDataList = null;
    private int cod;
    private String message;

    public int getCount() {
        return count;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return cod != 0;
    }
}