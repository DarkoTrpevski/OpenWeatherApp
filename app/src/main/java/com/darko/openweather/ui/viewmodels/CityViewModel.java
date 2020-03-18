package com.darko.openweather.ui.viewmodels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.repository.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CityViewModel extends ViewModel {

    private static final String TAG = "CityListViewModelTAG";

    private WeatherRepository weatherRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<List<WeatherData>> mutableWeatherDataList;
    public LiveData<List<WeatherData>> liveWeatherDataList;

    @Inject
    CityViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        disposable = new CompositeDisposable();
        mutableWeatherDataList = new MutableLiveData<>();
        liveWeatherDataList = mutableWeatherDataList;
    }

    public void insertWeatherDataToDB(String city) {
        weatherRepository.insertWeatherDataToDB(city);
    }

    public void deleteWeatherDataFromDB(WeatherData weatherData) {
        weatherRepository.deleteWeatherDataFromDB(weatherData);
    }

    public void updateWeatherList(String ids) {
        disposable.add(weatherRepository
                .updateWeatherDataList(ids)
                .subscribe(weatherData ->
                                mutableWeatherDataList.setValue(weatherData),
                        throwable ->
                                Log.d(TAG, "accept: updateWeatherList throwable triggered: " + throwable.getMessage())));
    }

    public LiveData<List<WeatherData>> loadWeatherDataListFromDB() {
        return weatherRepository.loadWeatherDataListFromDB();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}