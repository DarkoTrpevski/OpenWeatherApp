package com.darko.openweather.utils;

import com.darko.openweather.data.model.entity.WeatherData;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StringUtil {

    public static boolean doesCityExist(WeatherData newWeatherData, List<WeatherData> weatherDataList) {
        boolean exist = false;
        for (int i = 0; i < weatherDataList.size(); i++) {
            if (newWeatherData.getId() == weatherDataList.get(i).getId()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public static StringBuilder convertListToString(List<WeatherData> weatherDataList) {
        StringBuilder citiesId = new StringBuilder();

        for (int i = 0; i < weatherDataList.size(); i++) {
            citiesId.append(",").append(weatherDataList.get(i).getId());
        }
        if (citiesId.length() > 0) {
            citiesId.deleteCharAt(0);
        }
        return citiesId;
    }

    public static String convertDateToString(String date) {
        Date tempDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            tempDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime dateTime = new DateTime(tempDate);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MMMM, HH:mm");
        return formatter.print(dateTime);
    }

    public static String createUrl(String weatherIcon) {
        return "http://openweathermap.org/img/wn/" + weatherIcon + "@2x.png";
    }
}