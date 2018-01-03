package com.ahmed.deliveryzeyada.data.Remote;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class HeaderInterceptor implements Interceptor
{
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException
    {
        Request request = chain.request();
        request = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-type", "application/json")
                .build();

        return chain.proceed(request);
    }
}
