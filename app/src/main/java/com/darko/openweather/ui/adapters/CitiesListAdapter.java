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

import static com.darko.openweather.utils.StringUtil.createUrl;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CitiesHolder> {

    private List<WeatherData> weatherDataList;
    private Context context;

    private CityClickListener clickListener;

    public void setClickListener(CityClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public CitiesListAdapter(Context context) {
        this.context = context;
    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.weatherDataList = weatherDataList;
        notifyDataSetChanged();
    }

    public void addWeatherDataItem(WeatherData weatherData) {
        weatherDataList.add(weatherData);
        notifyDataSetChanged();
    }

    public void removeWeatherDataItem(int position) {
        weatherDataList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public CitiesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CitiesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CitiesHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        WeatherData weatherData = weatherDataList.get(adapterPosition);

        holder.city.setText(weatherData.getName());
        holder.temperature.setText(String.format("%s °C", Math.round(weatherData.getTemp())));

        Glide.with(context).load(createUrl(weatherData.getIcon())).into(holder.imageWeather);

        holder.itemView.setOnClickListener(v -> clickListener.onCityClicked(adapterPosition));
        holder.btnRemove.setOnClickListener(v -> clickListener.onRemoveClicked(adapterPosition));
    }

    @Override
    public int getItemCount() {
        return weatherDataList == null ? 0 : weatherDataList.size();
    }

    static class CitiesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_name)
        TextView city;

        @BindView(R.id.textView_temperature)
        TextView temperature;

        @BindView(R.id.imageView_weather)
        ImageView imageWeather;

        @BindView(R.id.btn_remove)
        ImageView btnRemove;

        CitiesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}