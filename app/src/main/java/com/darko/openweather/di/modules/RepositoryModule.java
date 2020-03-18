package com.darko.openweather.di.modules;

import com.darko.openweather.data.local.CitiesWeatherDatabase;
import com.darko.openweather.data.remote.WeatherAPIService;
import com.darko.openweather.data.remote.schedulers.SchedulerProvider;
import com.darko.openweather.di.scopes.ApplicationScope;
import com.darko.openweather.repository.WeatherRepository;
import com.darko.openweather.repository.datasources.BaseLocalSource;
import com.darko.openweather.repository.datasources.BaseRemoteSource;
import com.darko.openweather.repository.datasources.LocalDataSource;
import com.darko.openweather.repository.datasources.RemoteDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @ApplicationScope
    @Provides
    WeatherRepository provideRepository(BaseLocalSource localDataSource, BaseRemoteSource remoteDataSource) {
        return new WeatherRepository(localDataSource, remoteDataSource);
    }

    @ApplicationScope
    @Provides
    BaseRemoteSource provideRemoteDataSource(WeatherAPIService service, SchedulerProvider provider) {
        return new RemoteDataSource(service, provider);
    }

    @ApplicationScope
    @Provides
    BaseLocalSource provideLocalDataSource(CitiesWeatherDatabase database) {
        return new LocalDataSource(database);
    }
}