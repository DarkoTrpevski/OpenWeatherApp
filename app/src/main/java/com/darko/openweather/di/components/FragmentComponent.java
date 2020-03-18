package com.darko.openweather.di.components;

import com.darko.openweather.di.modules.FragmentModule;
import com.darko.openweather.di.scopes.FragmentScope;
import com.darko.openweather.ui.fragments.CityFragment;
import com.darko.openweather.ui.fragments.DetailFragment;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(CityFragment cityFragment);

    void inject(DetailFragment detailFragment);
}