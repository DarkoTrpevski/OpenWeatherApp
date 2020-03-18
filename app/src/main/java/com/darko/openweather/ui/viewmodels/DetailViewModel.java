package com.darko.openweather.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailViewModel extends ViewModel {

    private static final String TAG = "DetailCityViewModelTAG";

    private WeatherRepository weatherRepository;
    private CompositeDisposable disposable;

    private MutableLiveData<DetailWeatherResponse> mutableWeatherDetail;
    public LiveData<DetailWeatherResponse> liveWeatherDetail;

    @Inject
    DetailViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        disposable = new CompositeDisposable();
        mutableWeatherDetail = new MutableLiveData<>();
        liveWeatherDetail = mutableWeatherDetail;
    }

    public void getDetailCityWeather(String id) {
        disposable.add(weatherRepository.loadDetailCityWeather(id)
                .subscribe(detailWeatherResponse ->
                                mutableWeatherDetail.setValue(detailWeatherResponse),
                        throwable -> {
                            mutableWeatherDetail.setValue(null);
                            Log.d(TAG, "accept: throwable in DetailViewModel triggered: " + throwable.getMessage());
                        }));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}