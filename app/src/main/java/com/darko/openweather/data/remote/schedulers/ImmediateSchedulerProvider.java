package com.darko.openweather.data.remote.schedulers;

import androidx.annotation.NonNull;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class ImmediateSchedulerProvider implements SchedulerProvider {

    @Inject
    ImmediateSchedulerProvider() {
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}