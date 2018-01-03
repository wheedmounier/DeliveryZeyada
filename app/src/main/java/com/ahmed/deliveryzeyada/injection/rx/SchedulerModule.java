package com.ahmed.deliveryzeyada.injection.rx;

import com.ahmed.deliveryzeyada.rx.RunOn;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.ahmed.deliveryzeyada.rx.SchedulerType.COMPUTATION;
import static com.ahmed.deliveryzeyada.rx.SchedulerType.IO;
import static com.ahmed.deliveryzeyada.rx.SchedulerType.UI;

/**
 * Created by Ahmed Kamal on 03-01-2018.
 */

@Module
public class SchedulerModule
{
    @Provides
    @RunOn(IO)
    Scheduler provideIo(){
        return Schedulers.io();
    }

    @Provides
    @RunOn(COMPUTATION)
    Scheduler provideComputation() {
        return Schedulers.computation();
    }

    @Provides
    @RunOn(UI)
    Scheduler provideUi() {
        return AndroidSchedulers.mainThread();
    }
}
