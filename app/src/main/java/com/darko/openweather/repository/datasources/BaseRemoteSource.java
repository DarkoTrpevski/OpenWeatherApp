package com.darko.openweather.repository.datasources;

import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.data.remote.response.WeatherListResponse;

import io.reactivex.Observable;

public interface BaseRemoteSource {
    Observable<WeatherData> loadCityWeatherFromAPI(String city);

    Observable<WeatherListResponse> loadCitiesWeatherFromAPI(String citiesId);

    Observable<DetailWeatherResponse> loadDetailCityWeatherFromAPI(String cityId);
}