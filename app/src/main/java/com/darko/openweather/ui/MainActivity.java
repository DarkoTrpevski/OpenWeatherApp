package com.darko.openweather.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.darko.openweather.R;
import com.darko.openweather.base.BaseActivity;
import com.darko.openweather.ui.fragments.CityFragment;
import com.darko.openweather.ui.navigators.BaseNavigator;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    BaseNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigator.replaceFragment(getSupportFragmentManager(), new CityFragment(), R.id.container, false);
    }

    @Override
    protected void injectActivity() {
        getActivityComponent().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBackButtonEnabled(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
            getSupportActionBar().setHomeButtonEnabled(enabled);
        }
    }
}
