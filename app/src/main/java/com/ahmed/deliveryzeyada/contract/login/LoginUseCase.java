package com.ahmed.deliveryzeyada.contract.login;

import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginResponse;

import io.reactivex.Single;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public interface LoginUseCase
{
    Single<LoginResponse> validateCredentials(String userName , String password);
}
