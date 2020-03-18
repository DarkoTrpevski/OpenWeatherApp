package com.darko.openweather.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darko.openweather.R;
import com.darko.openweather.base.BaseFragment;
import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.data.remote.response.DetailWeatherResponse;
import com.darko.openweather.ui.MainActivity;
import com.darko.openweather.ui.adapters.ForecastAdapter;
import com.darko.openweather.ui.viewmodels.DetailViewModel;
import com.darko.openweather.utils.Connectivity;
import com.darko.openweather.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import static com.darko.openweather.utils.Constants.CITY_ID;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"deprecation"})
public class DetailFragment extends BaseFragment {

    @BindView(R.id.textView_city)
    TextView textViewCity;

    @BindView(R.id.textView_weather)
    TextView textViewWeather;

    @BindView(R.id.textView_current_temperature)
    TextView textViewCurrentTemperature;

    @BindView(R.id.textView_temperature_min_max)
    TextView textViewTemperatureMinMax;

    @BindView(R.id.textView_pressure)
    TextView textViewPressure;

    @BindView(R.id.textView_humidity)
    TextView textViewHumidity;

    @BindView(R.id.recycler_view_forecast)
    RecyclerView recyclerViewForecast;

    private DetailViewModel detailViewModel;
    private ForecastAdapter adapter;
    private int cityID;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setBackButtonEnabled(true);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutRes(), container, false);
        ButterKnife.bind(this, view);

        initUI();
        if (getArguments() != null) {
            cityID = getArguments().getInt(CITY_ID);
        }

        detailViewModel.liveWeatherDetail.observe(getViewLifecycleOwner(), this::setDataToUI);

        if (Connectivity.isConnected(getContext())) {
            detailViewModel.getDetailCityWeather(String.valueOf(cityID));
        } else {
            Toast.makeText(getContext(), "Internet is required to get up to date info", Toast.LENGTH_LONG).show();

        }
        return view;
    }

    private void setDataToUI(DetailWeatherResponse data) {
        WeatherData weatherData = data.getWeatherDataList().get(0);

        textViewCity.setText(data.getCity().getName());
        textViewWeather.setText(weatherData.getWeather().get(0).getDescription());
        textViewCurrentTemperature.setText(String.format("%s °C", Math.round(weatherData.getMain().getTemp())));
        textViewTemperatureMinMax.setText(String.format("%s°/%s°", Math.round(weatherData.getMain().getTempMax()), Math.round(weatherData.getMain().getTempMin())));
        textViewPressure.setText(String.format("%s %s", weatherData.getMain().getPressure(), "hPa"));
        textViewHumidity.setText(String.format("%s %s ", weatherData.getMain().getHumidity(), "%"));

        adapter.setData(data.getWeatherDataList());
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_detail_city;
    }

    @Override
    protected void injectFragment() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initUI() {
        adapter = new ForecastAdapter(getContext());
        recyclerViewForecast.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        ScaleInAnimationAdapter newsAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        newsAnimationAdapter.setFirstOnly(false);
        recyclerViewForecast.setAdapter(newsAnimationAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setBackButtonEnabled(false);
        }
    }
}