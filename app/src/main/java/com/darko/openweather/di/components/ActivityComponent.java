package com.darko.openweather.di.components;

import com.darko.openweather.di.modules.ActivityModule;
import com.darko.openweather.di.modules.FragmentModule;
import com.darko.openweather.di.scopes.ActivityScope;
import com.darko.openweather.ui.MainActivity;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    FragmentComponent newFragmentComponent(FragmentModule fragmentModule);

    void inject(MainActivity mainActivity);
}