package com.ahmed.deliveryzeyada.data.Remote.api.login;

import com.ahmed.deliveryzeyada.data.Remote.api.login.model.User;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.ahmed.deliveryzeyada.utils.Constants.CHECK_USER_API;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public interface LoginService
{
    @GET(CHECK_USER_API)
    Single<LoginResponse> checkUser(@Query("USER") String userName , @Query("pass") String pass);

}
