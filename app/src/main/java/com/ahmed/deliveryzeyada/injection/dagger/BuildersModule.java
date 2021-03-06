package com.ahmed.deliveryzeyada.injection.dagger;

import com.ahmed.deliveryzeyada.injection.login.LoginModule;
import com.ahmed.deliveryzeyada.injection.maps.MapsModule;
import com.ahmed.deliveryzeyada.injection.rx.SchedulerModule;
import com.ahmed.deliveryzeyada.injection.service.ApiServiceModule;
import com.ahmed.deliveryzeyada.presentation.Login.LoginActivity;
import com.ahmed.deliveryzeyada.presentation.maps.PilotMapActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */
@Module
public abstract class BuildersModule
{
    @ContributesAndroidInjector(modules = {LoginModule.class , ApiServiceModule.class , SchedulerModule.class})
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {MapsModule.class , ApiServiceModule.class , SchedulerModule.class})
    abstract PilotMapActivity bindPilotMapActivity();
}
