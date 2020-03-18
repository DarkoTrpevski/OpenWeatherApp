package com.darko.openweather.ui.navigators;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public interface BaseNavigator {
    /**
     * @param manager        the fragmentManager to create the transaction
     * @param fragment       the fragment to be opened
     * @param containerID    the container to be replaced
     * @param addToBackStack fragment shouldn't be saved to backStack
     */
    void replaceFragment(FragmentManager manager, Fragment fragment, int containerID, boolean addToBackStack);

    /**
     * @param manager         the fragmentManager to create the transaction
     * @param fragment        the fragment to be opened
     * @param containerID     the container to be replaced
     * @param bundle          the bundle extras
     * @param addToBackStack fragment shouldn't be saved to backStack
     */

    void replaceFragmentWithBundle(FragmentManager manager, Fragment fragment, int containerID, Bundle bundle, boolean addToBackStack);

    /**
     * Start a new Activity for a result.
     *
     * @param cls         the Activity class to be opened.
     * @param requestCode the request code that will be passed to the opened Activity.
     */
    void startActivityForResult(Class cls, int requestCode);

    /**
     * Start a new Activity for a result with an extra
     *
     * @param cls         the Activity class to be opened.
     * @param requestCode the request code that will be passed to the opened Activity.
     * @param extraKey    the key for the extra that is passed in the Intent.
     * @param extraValue  the value for the extra the is passed in the Intent.
     */
    void startActivityForResultWithExtra(Class cls, int requestCode, String extraKey, String extraValue);
}