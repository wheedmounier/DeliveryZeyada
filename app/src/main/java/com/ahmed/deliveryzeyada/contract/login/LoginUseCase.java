package com.ahmed.deliveryzeyada.contract.login;

import com.ahmed.deliveryzeyada.data.Remote.api.login.model.LoginResponse;

import io.reactivex.Single;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public interface LoginUseCase
{
    Single<LoginResponse> validateCredentials(String email , String password , String grantType , String deviceId);
    Single<String> checkUser(String email , String password);
}
