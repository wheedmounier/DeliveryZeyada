package com.ahmed.deliveryzeyada.injection;

import com.ahmed.deliveryzeyada.DeliveryApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class ,
        AppModule.class , BuildersModule.class})

public interface AppComponent
{
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder application(DeliveryApp app);
        AppComponent build();
    }
    void inject(DeliveryApp app);
}
