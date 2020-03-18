package com.darko.openweather.di.modules;

import androidx.lifecycle.ViewModel;

import com.darko.openweather.di.scopes.ViewModelKey;
import com.darko.openweather.ui.viewmodels.DetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    public abstract ViewModel bindSearchViewModel(DetailViewModel detailViewModel);
}