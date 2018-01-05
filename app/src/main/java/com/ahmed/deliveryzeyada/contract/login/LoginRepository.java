package com.ahmed.deliveryzeyada.contract.login;

import com.ahmed.deliveryzeyada.data.Remote.api.login.model.LoginResponse;
import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginService;
import com.ahmed.deliveryzeyada.data.Remote.api.login.model.User;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class LoginRepository implements LoginUseCase
{
    private LoginService service;

    @Inject
    public LoginRepository(LoginService service)
    {
        this.service = service;
    }


    @Override
    public Single<LoginResponse> validateCredentials(String email, String password, String grantType, String deviceId)
    {
        return service.checkUser(email , password , grantType , deviceId);
    }

    @Override
    public Single<String> checkUser(String email, String password)
    {
        return service.validateUser(email, password);
    }
}
