package com.darko.openweather.data.remote.schedulers;
import androidx.annotation.NonNull;
import io.reactivex.Scheduler;
public interface SchedulerProvider {
    @NonNull
    Scheduler computation();
    @NonNull
    Scheduler io();
    @NonNull
    Scheduler ui();
}