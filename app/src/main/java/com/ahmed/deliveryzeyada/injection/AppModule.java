package com.ahmed.deliveryzeyada.injection;

import android.content.Context;

import com.ahmed.deliveryzeyada.DeliveryApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */
@Module
public class AppModule
{
    @Provides
    Context provideContext(DeliveryApp application) {
        return application.getApplicationContext();
    }
}
