package com.darko.openweather.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.darko.openweather.di.components.ActivityComponent;
import com.darko.openweather.di.components.FragmentComponent;
import com.darko.openweather.di.modules.FragmentModule;
import com.darko.openweather.base.BaseActivity;

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Override
    public void onAttach(@NonNull Context context) {
        initFragmentComponent();
        injectFragment();
        super.onAttach(context);
    }

    private void initFragmentComponent() {
        ActivityComponent activityComponent = getActivityComponent();
        if (activityComponent != null) {
            fragmentComponent = activityComponent.newFragmentComponent(new FragmentModule(this));
        }
    }

    protected abstract int provideLayoutRes();

    protected abstract void injectFragment();

    protected abstract void initUI();

    private ActivityComponent getActivityComponent() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getActivityComponent();
        } else {
            return null;
        }
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }
}