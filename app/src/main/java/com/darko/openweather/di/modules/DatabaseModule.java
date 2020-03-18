package com.darko.openweather.di.modules;

import android.app.Application;

import com.darko.openweather.data.local.CitiesWeatherDatabase;
import com.darko.openweather.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @ApplicationScope
    @Provides
    CitiesWeatherDatabase provideDatabase(Application application) {
        return CitiesWeatherDatabase.getInstance(application.getApplicationContext());
    }
}