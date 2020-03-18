package com.darko.openweather.repository.datasources;

import androidx.lifecycle.LiveData;

import com.darko.openweather.data.local.CitiesWeatherDao;
import com.darko.openweather.data.local.CitiesWeatherDatabase;
import com.darko.openweather.data.model.entity.WeatherData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalDataSource implements BaseLocalSource {

    private CitiesWeatherDao weatherDao;
    private Executor executor;

    public LocalDataSource(CitiesWeatherDatabase database) {
        this.weatherDao = database.citiesWeatherDao();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void insertWeatherDataToDB(WeatherData weatherData) {
        executor.execute(() -> weatherDao.insert(weatherData));
    }

    @Override
    public void deleteWeatherDataFromDB(WeatherData weatherData) {
        executor.execute(() -> weatherDao.delete(weatherData));
    }

    @Override
    public LiveData<List<WeatherData>> loadWeatherDataListFromDB() {
        return weatherDao.loadWeatherList();
    }
}