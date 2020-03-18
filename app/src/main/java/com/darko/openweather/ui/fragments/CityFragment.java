package com.darko.openweather.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darko.openweather.R;
import com.darko.openweather.base.BaseFragment;
import com.darko.openweather.data.model.entity.WeatherData;
import com.darko.openweather.ui.adapters.CitiesListAdapter;
import com.darko.openweather.ui.adapters.CityClickListener;
import com.darko.openweather.ui.navigators.BaseNavigator;
import com.darko.openweather.ui.viewmodels.CityViewModel;
import com.darko.openweather.utils.Connectivity;
import com.darko.openweather.utils.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.darko.openweather.utils.Constants.CITY_ID;
import static com.darko.openweather.utils.StringUtil.convertListToString;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings({"deprecation"})
public class CityFragment extends BaseFragment implements CityClickListener {

    private static final String TAG = "CitiesListFragmentTAG";

    @BindView(R.id.recycler_view_cities)
    RecyclerView recyclerViewCities;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private CityViewModel cityViewModel;
    private CitiesListAdapter adapter;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    BaseNavigator navigator;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cityViewModel = ViewModelProviders.of(this, viewModelFactory).get(CityViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(provideLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeLiveData();
    }

    private void observeLiveData() {
        cityViewModel
                .loadWeatherDataListFromDB()
                .observe(getViewLifecycleOwner(), weatherDataList -> {
                    adapter.setWeatherDataList(weatherDataList);
                    if (Connectivity.isConnected(getContext())) {
                        updateWeatherList(weatherDataList);
                    } else {
                        Toast.makeText(getContext(), "Internet is required to get up to date info", Toast.LENGTH_LONG).show();
                    }
                });
        cityViewModel
                .liveWeatherDataList
                .observe(getViewLifecycleOwner(), weatherDataList -> adapter.setWeatherDataList(weatherDataList));
    }

    private void updateWeatherList(List<WeatherData> updatedWeatherList) {
        StringBuilder citiesId = convertListToString(updatedWeatherList);
        cityViewModel.updateWeatherList(citiesId.toString());
    }

    private void addCityDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.city_dialog, null);

        EditText editText = view.findViewById(R.id.editText_city);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view).setTitle(getResources().getString(R.string.add_city))
                .setPositiveButton(getResources().getString(R.string.ok), (dialog, id) -> {
                    if (!TextUtils.isEmpty(editText.getText().toString()))
                        addCity(editText.getText().toString());
                })
                .setNegativeButton(getResources().getString(R.string.cancel), (dialog, id) ->
                        dialog.dismiss());
        builder.create();
        builder.show();
    }

    private void addCity(String city) {
        cityViewModel.insertWeatherDataToDB(city);
    }

    @OnClick(R.id.fab)
    void onFabClick(View view) {
        addCityDialog();
    }

    @Override
    protected int provideLayoutRes() {
        return R.layout.fragment_cities_list;
    }

    @Override
    protected void injectFragment() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initUI() {
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new CitiesListAdapter(getContext());
        adapter.setClickListener(this);
        recyclerViewCities.setAdapter(adapter);
    }

    @Override
    public void onCityClicked(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(CITY_ID, adapter.getWeatherDataList().get(position).getId());
        navigator.replaceFragmentWithBundle(getFragmentManager(), new DetailFragment(), R.id.container, bundle, true);
    }

    @Override
    public void onRemoveClicked(int position) {
        cityViewModel.deleteWeatherDataFromDB(adapter.getWeatherDataList().get(position));
        adapter.removeWeatherDataItem(position);
        Snackbar.make(recyclerViewCities, getResources().getString(R.string.city_deleted), Snackbar.LENGTH_SHORT).show();
    }
}