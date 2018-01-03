package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ahmed.deliveryzeyada.contract.login.LoginUseCase;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import javax.inject.Inject;

import io.reactivex.Scheduler;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class LoginViewModelFactory implements ViewModelProvider.Factory
{
    private final LoginUseCase loginUseCase;
    private Scheduler ioScheduler , mainScheduler;

    @Inject
    public LoginViewModelFactory(LoginUseCase loginUseCase,
           @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        this.loginUseCase = loginUseCase;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        if(modelClass.isAssignableFrom(LoginViewModel.class))
        {
            return (T) new LoginViewModel(loginUseCase , ioScheduler , mainScheduler);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
