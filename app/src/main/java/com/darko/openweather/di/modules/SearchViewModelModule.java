package com.darko.openweather.di.modules;

import androidx.lifecycle.ViewModel;

import com.darko.openweather.di.scopes.ViewModelKey;
import com.darko.openweather.ui.viewmodels.CityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel.class)
    public abstract ViewModel bindSearchViewModel(CityViewModel cityViewModel);
}