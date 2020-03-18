package com.darko.openweather.base;

import android.app.Application;

import com.darko.openweather.di.components.ApplicationComponent;
import com.darko.openweather.di.components.DaggerApplicationComponent;
import com.darko.openweather.di.modules.AppModule;

public abstract class BaseApp extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        injectIfNecessary();
    }

    private void injectIfNecessary() {
        injectApp();
    }

    protected abstract void injectApp();

    private void initAppComponent() {
        appComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}