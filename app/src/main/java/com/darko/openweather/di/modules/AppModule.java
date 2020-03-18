package com.darko.openweather.di.modules;

import android.app.Application;

import com.darko.openweather.data.remote.schedulers.DefaultSchedulerProvider;
import com.darko.openweather.data.remote.schedulers.SchedulerProvider;
import com.darko.openweather.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    public Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationScope
    public SchedulerProvider provideSchedulerProvider() {
        return new DefaultSchedulerProvider();
    }
}