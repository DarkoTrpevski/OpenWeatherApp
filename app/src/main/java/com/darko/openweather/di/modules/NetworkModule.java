package com.darko.openweather.di.modules;

import com.darko.openweather.data.remote.WeatherAPIService;
import com.darko.openweather.di.scopes.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.darko.openweather.utils.Constants.BASE_URL;
import static com.darko.openweather.utils.Constants.TIMEOUT_REQUEST;

@Module
public class NetworkModule {

    @ApplicationScope
    @Provides
    HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @ApplicationScope
    @Provides
    OkHttpClient okHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .build();
    }

    @ApplicationScope
    @Provides
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @ApplicationScope
    @Provides
    WeatherAPIService openWeatherServiceApi(Retrofit retrofit) {
        return retrofit.create(WeatherAPIService.class);
    }
}