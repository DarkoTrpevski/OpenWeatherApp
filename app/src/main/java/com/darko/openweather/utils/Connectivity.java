package com.darko.openweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@SuppressWarnings("deprecation")
public class Connectivity {

    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        } else {
            return null;
        }
    }

    public static boolean isConnected(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}