package com.darko.openweather.repository.datasources;

import androidx.lifecycle.LiveData;

import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;

import java.util.List;

import io.reactivex.Observable;

public interface BaseRepository {
    void insertWeatherDataToDB(String cityName);

    void deleteWeatherDataFromDB(WeatherData weatherData);

    Observable<List<WeatherData>> updateWeatherDataList(String citiesId);

    Observable<DetailWeatherResponse> loadDetailCityWeather(String cityID);

    LiveData<List<WeatherData>> loadWeatherDataListFromDB();
}