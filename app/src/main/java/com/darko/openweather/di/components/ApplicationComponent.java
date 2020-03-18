package com.darko.openweather.di.components;

import com.darko.openweather.WeatherApp;
import com.darko.openweather.di.modules.ActivityModule;
import com.darko.openweather.di.modules.AppModule;
import com.darko.openweather.di.modules.DatabaseModule;
import com.darko.openweather.di.modules.DetailViewModelModule;
import com.darko.openweather.di.modules.NetworkModule;
import com.darko.openweather.di.modules.RepositoryModule;
import com.darko.openweather.di.modules.SearchViewModelModule;
import com.darko.openweather.di.modules.ViewModelFactoryModule;
import com.darko.openweather.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        NetworkModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        SearchViewModelModule.class,
        DetailViewModelModule.class,
        RepositoryModule.class,
        DatabaseModule.class})
public interface ApplicationComponent {
    ActivityComponent newActivityComponent(ActivityModule activityModule);

    void inject(WeatherApp app);
}