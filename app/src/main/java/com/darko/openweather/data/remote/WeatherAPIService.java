package com.darko.openweather.data.remote;


import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.data.remote.response.WeatherListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIService {

    @GET("weather")
    Observable<WeatherData> getCityWeatherByName(@Query("q") String city,
                                                 @Query("units") String units,
                                                 @Query("lang") String lang,
                                                 @Query("appid") String appID);

    @GET("group")
    Observable<WeatherListResponse> getCitiesWeather(@Query("id") String ids,
                                                     @Query("units") String units,
                                                     @Query("lang") String lang,
                                                     @Query("appid") String appID);

    @GET("forecast")
    Observable<DetailWeatherResponse> getDetailWeather(@Query("id") String ids,
                                                       @Query("units") String units,
                                                       @Query("lang") String lang,
                                                       @Query("appid") String appID);
}