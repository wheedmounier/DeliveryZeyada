package com.ahmed.deliveryzeyada;

import android.app.Activity;
import android.app.Application;

import com.ahmed.deliveryzeyada.injection.dagger.DaggerAppComponent;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class DeliveryApp extends Application implements HasActivityInjector
{

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static String token;

    @Override
    public void onCreate()
    {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        DaggerAppComponent.builder().
                application(this).build().
                inject(this);

        FirebaseApp.initializeApp(this);
        token = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public AndroidInjector<Activity> activityInjector()
    {
        return dispatchingAndroidInjector;
    }

    public static String getFirebaseToken()
    {
        return token;
    }
}
