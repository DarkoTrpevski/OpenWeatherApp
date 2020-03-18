package com.darko.openweather.di.modules;

import androidx.fragment.app.Fragment;

import com.darko.openweather.di.scopes.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment fragment() {
        return this.fragment;
    }
}