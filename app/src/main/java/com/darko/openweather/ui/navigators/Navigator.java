package com.darko.openweather.ui.navigators;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

/**
 * Implementation of the {@link BaseNavigator}.
 */

public class Navigator implements BaseNavigator {

    //*TREBA DA VIDAM KAKVO ACTIVITY TREBASE DA INJECT-NAM
    private AppCompatActivity activity;

    @Inject
    public Navigator(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void replaceFragment(FragmentManager manager, Fragment fragment, int containerID, boolean saveToBackStack) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (saveToBackStack) {
            transaction.addToBackStack(null);
        } else {
            manager.popBackStackImmediate();
        }
        transaction.replace(containerID, fragment, fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void replaceFragmentWithBundle(FragmentManager manager, Fragment fragment, int containerID, Bundle bundle, boolean saveToBackStack) {
        FragmentTransaction transaction = manager.beginTransaction();
        if (saveToBackStack) {
            transaction.addToBackStack(null);
        } else {
            manager.popBackStackImmediate();
        }
        fragment.setArguments(bundle);
        transaction.replace(containerID, fragment, fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void startActivityForResult(Class cls, int requestCode) {
        Intent i = new Intent(activity, cls);
        activity.startActivity(i);
    }

    @Override
    public void startActivityForResultWithExtra(Class cls, int requestCode, String extraKey, String extraValue) {
        Intent i = new Intent(activity, cls);
        i.putExtra(extraKey, extraValue);
        activity.startActivity(i);
    }
}