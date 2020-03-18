package com.darko.openweather.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darko.openweather.R;
import com.darko.openweather.data.model.entity.WeatherData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.darko.openweather.utils.StringUtil.convertDateToString;
import static com.darko.openweather.utils.StringUtil.createUrl;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastHolder> {

    private List<WeatherData> weatherDataList;
    private Context context;

    public ForecastAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<WeatherData> data) {
        this.weatherDataList = data;
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ForecastHolder holder, int position) {

        WeatherData weatherData = weatherDataList.get(position);

        holder.temperature.setText(String.format("%s °C", Math.round(weatherData.getMain().getTemp())));
        holder.weather.setText(weatherData.getWeather().get(0).getDescription());
        holder.date.setText(convertDateToString(weatherData.getDate()));

        Glide.with(context).load(createUrl(weatherData.getWeather().get(0).getIcon())).into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        if (weatherDataList != null) {
            return weatherDataList.size();
        } else {
            return 0;
        }
    }

    static class ForecastHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_temperature)
        TextView temperature;

        @BindView(R.id.textView_weather)
        TextView weather;

        @BindView(R.id.textView_date)
        TextView date;

        @BindView(R.id.imageView_weather)
        ImageView weatherIcon;

        ForecastHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}