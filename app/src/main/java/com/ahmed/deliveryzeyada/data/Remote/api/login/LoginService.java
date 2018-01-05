package com.ahmed.deliveryzeyada.data.Remote.api.login;

import com.ahmed.deliveryzeyada.data.Remote.api.login.model.LoginResponse;
import com.ahmed.deliveryzeyada.data.Remote.api.login.model.User;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.ahmed.deliveryzeyada.utils.Constants.CHECK_USER;
import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_API;
import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_DEVICE_ID;
import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_EMAIL;
import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_GRANT_TYPE;
import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_PASSWORD;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public interface LoginService
{
    @FormUrlEncoded
    @POST(LOGIN_API)
    Single<LoginResponse> checkUser(@Field(LOGIN_EMAIL) String email ,
                                    @Field(LOGIN_PASSWORD) String password ,
                                    @Field(LOGIN_GRANT_TYPE) String grantType ,
                                    @Field(LOGIN_DEVICE_ID) String deviceID);

    @GET(CHECK_USER)
    Single<String> validateUser(@Query("email") String email , @Query("password") String password);

}
