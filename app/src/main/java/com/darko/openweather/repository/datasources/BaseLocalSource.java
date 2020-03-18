package com.darko.openweather.repository.datasources;

import androidx.lifecycle.LiveData;

import com.darko.openweather.data.model.entity.WeatherData;

import java.util.List;

public interface BaseLocalSource {
    void insertWeatherDataToDB(WeatherData weatherData);

    void deleteWeatherDataFromDB(WeatherData weatherData);

    LiveData<List<WeatherData>> loadWeatherDataListFromDB();
}