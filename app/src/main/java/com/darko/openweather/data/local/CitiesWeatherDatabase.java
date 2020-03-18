package com.darko.openweather.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.utils.Constants;

@Database(entities = WeatherData.class, version = 1, exportSchema = false)
public abstract class CitiesWeatherDatabase extends RoomDatabase {
    private static CitiesWeatherDatabase INSTANCE;
    public abstract CitiesWeatherDao citiesWeatherDao();
    public static synchronized CitiesWeatherDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CitiesWeatherDatabase.class, Constants.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}