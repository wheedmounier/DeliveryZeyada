package com.ahmed.deliveryzeyada.injection.maps;

import com.ahmed.deliveryzeyada.contract.maps.MapsRepository;
import com.ahmed.deliveryzeyada.contract.maps.MapsUseCase;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.MapsService;
import com.ahmed.deliveryzeyada.presentation.maps.MapsViewModelFactory;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */
@Module
public class MapsModule
{
    @Provides
    MapsViewModelFactory provideMapsViewModelFactory(MapsUseCase mapsUseCase ,
              @RunOn(SchedulerType.IO) Scheduler ioScheduler , @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        return new MapsViewModelFactory(mapsUseCase , ioScheduler , mainScheduler);
    }

    @Provides
    MapsUseCase provideMapsUseCase(MapsService mapsService)
    {
        return new MapsRepository(mapsService);
    }
}
