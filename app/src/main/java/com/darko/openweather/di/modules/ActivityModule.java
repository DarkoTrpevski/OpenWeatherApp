package com.darko.openweather.di.modules;


import androidx.appcompat.app.AppCompatActivity;

import com.darko.openweather.di.scopes.ActivityScope;
import com.darko.openweather.ui.navigators.BaseNavigator;
import com.darko.openweather.ui.navigators.Navigator;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    BaseNavigator provideNavigator(AppCompatActivity activity) {
        return new Navigator(activity);
    }

    @Provides
    @ActivityScope
    AppCompatActivity activity() {
        return this.activity;
    }

}