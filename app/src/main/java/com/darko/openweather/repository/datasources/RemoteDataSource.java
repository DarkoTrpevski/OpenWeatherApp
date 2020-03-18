package com.darko.openweather.repository.datasources;


import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.WeatherAPIService;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.data.remote.response.WeatherListResponse;
import com.darko.openweather.data.remote.schedulers.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;

import static com.darko.openweather.utils.Constants.APP_ID;

public class RemoteDataSource implements BaseRemoteSource {

    private final String UNITS = "metric";
    private final String LANG = "en";

    private WeatherAPIService service;
    private SchedulerProvider provider;

    @Inject
    public RemoteDataSource(WeatherAPIService service, SchedulerProvider provider) {
        this.service = service;
        this.provider = provider;
    }

    public Observable<WeatherData> loadCityWeatherFromAPI(String city) {
        return service.getCityWeatherByName(city, UNITS, LANG, APP_ID)
                .subscribeOn(provider.io())
                .observeOn(provider.ui());
    }

    public Observable<WeatherListResponse> loadCitiesWeatherFromAPI(String citiesId) {
        return service.getCitiesWeather(citiesId, UNITS, LANG, APP_ID)
                .subscribeOn(provider.io())
                .observeOn(provider.ui());
    }

    public Observable<DetailWeatherResponse> loadDetailCityWeatherFromAPI(String cityId) {
        return service.getDetailWeather(cityId, UNITS, LANG, APP_ID)
                .subscribeOn(provider.io())
                .observeOn(provider.ui());
    }
}