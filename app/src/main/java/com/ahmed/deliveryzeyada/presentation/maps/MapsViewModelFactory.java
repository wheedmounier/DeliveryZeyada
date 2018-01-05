package com.ahmed.deliveryzeyada.presentation.maps;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ahmed.deliveryzeyada.contract.maps.MapsUseCase;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import javax.inject.Inject;

import io.reactivex.Scheduler;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public class MapsViewModelFactory implements ViewModelProvider.Factory
{
    private MapsUseCase mapsUseCase;
    private Scheduler ioScheduler , mainScheduler;

    @Inject
    public MapsViewModelFactory(MapsUseCase mapsUseCase,
           @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        this.mapsUseCase = mapsUseCase;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if(modelClass.isAssignableFrom(MapsViewModel.class))
        {
            return (T) new MapsViewModel(mapsUseCase , ioScheduler , mainScheduler);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
