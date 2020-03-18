package com.darko.openweather.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.darko.openweather.base.BaseApp;
import com.darko.openweather.di.components.ActivityComponent;
import com.darko.openweather.di.components.ApplicationComponent;
import com.darko.openweather.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initActivityComponent();
        injectActivity();
        super.onCreate(savedInstanceState);
    }

    private void initActivityComponent() {
        activityComponent = getAppComponent().newActivityComponent(new ActivityModule(this));
    }

    protected abstract void injectActivity();

    protected ApplicationComponent getAppComponent() {
        return ((BaseApp) getApplication()).getAppComponent();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}