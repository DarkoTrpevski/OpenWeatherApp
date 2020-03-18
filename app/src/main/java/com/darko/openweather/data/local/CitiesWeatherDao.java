package com.darko.openweather.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.darko.openweather.data.model.entity.WeatherData;

import java.util.List;

@Dao
public interface CitiesWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WeatherData weatherData);

    @Delete
    void delete(WeatherData weatherData);

    @Query("SELECT * FROM cities_weather")
    LiveData<List<WeatherData>> loadWeatherList();
}