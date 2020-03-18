package com.darko.openweather.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.repository.datasources.BaseLocalSource;
import com.darko.openweather.repository.datasources.BaseRemoteSource;
import com.darko.openweather.repository.datasources.BaseRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class WeatherRepository implements BaseRepository {

    private static final String TAG = "RepositoryTAG";

    private BaseLocalSource localDataSource;
    private BaseRemoteSource remoteDataSource;

    @Inject
    public WeatherRepository(BaseLocalSource localDataSource, BaseRemoteSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void insertWeatherDataToDB(String cityName) {
        remoteDataSource
                .loadCityWeatherFromAPI(cityName)
                .subscribe(weatherData -> localDataSource.insertWeatherDataToDB(weatherData),
                        throwable -> {
                            Log.d(TAG, "accept: throwable in insertWeatherDataToDB triggered: " + throwable.getMessage());
                            throwable.printStackTrace();
                        });
    }

    @Override
    public void deleteWeatherDataFromDB(WeatherData weatherData) {
        localDataSource.deleteWeatherDataFromDB(weatherData);
    }

    @Override
    public Observable<List<WeatherData>> updateWeatherDataList(String citiesId) {
        return remoteDataSource
                .loadCitiesWeatherFromAPI(citiesId)
                .map(weatherListResponse -> {
                    if (!weatherListResponse.isError()) {
                        return weatherListResponse.getWeatherDataList();
                    } else {
                        Log.d(TAG, "updateWeatherList: weatherListResponse might be null");
                        return null;
                    }
                });
    }

    @Override
    public Observable<DetailWeatherResponse> loadDetailCityWeather(String cityID) {
        return remoteDataSource.loadDetailCityWeatherFromAPI(cityID);
    }

    @Override
    public LiveData<List<WeatherData>> loadWeatherDataListFromDB() {
        return localDataSource.loadWeatherDataListFromDB();
    }
}