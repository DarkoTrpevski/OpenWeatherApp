package com.darko.openweather.di.modules;

import androidx.lifecycle.ViewModel;

import com.darko.openweather.di.scopes.ApplicationScope;
import com.darko.openweather.utils.ViewModelFactory;

import java.util.Map;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelFactoryModule {
    @Provides
    @ApplicationScope
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }
}