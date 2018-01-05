package com.ahmed.deliveryzeyada.injection.service;

import com.ahmed.deliveryzeyada.data.Remote.HeaderInterceptor;
import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginService;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.MapsService;
import com.ahmed.deliveryzeyada.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */
@Module
public class ApiServiceModule
{
    private static final String BASE_URL = "base_url";

    @Provides
    MapsService provideMapsService(Retrofit retrofit)
    {
        return retrofit.create(MapsService.class);
    }

    @Provides
    LoginService provideLoginService(Retrofit retrofit)
    {
        return retrofit.create(LoginService.class);
    }

    @Provides
    @Named(BASE_URL)
    String provideBaseUrl() {
        return Constants.BASE_URL;
    }

    @Provides
    HeaderInterceptor provideHeaderInterceptor() {
        return new HeaderInterceptor();
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor()
    {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    OkHttpClient provideHttpClient(HeaderInterceptor headerInterceptor,
                                   HttpLoggingInterceptor httpInterceptor)
    {
        return new OkHttpClient.Builder().addInterceptor(headerInterceptor)
                .addInterceptor(httpInterceptor)
                .build();
    }

    @Provides
    Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    CallAdapter.Factory provideRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory, OkHttpClient client)
    {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }
}
