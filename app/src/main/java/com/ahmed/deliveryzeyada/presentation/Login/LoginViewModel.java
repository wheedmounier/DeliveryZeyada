package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ahmed.deliveryzeyada.contract.login.LoginUseCase;
import com.ahmed.deliveryzeyada.data.Remote.Response;
import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginResponse;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class LoginViewModel extends ViewModel
{
    private final LoginUseCase loginUseCase;
    private Scheduler ioScheduler , mainScheduler;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Response<LoginResponse>> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase, @RunOn(SchedulerType.IO) Scheduler ioScheduler,
                          @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        this.loginUseCase = loginUseCase;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    public MutableLiveData<Response<LoginResponse>> getLoginResponse()
    {
        return loginResponse;
    }

    public MutableLiveData<Boolean> getLoadingStatus()
    {
        return loadingStatus;
    }

    @Override
    protected void onCleared()
    {
        super.onCleared();
        compositeDisposable.clear();
    }

    private void addDisposable(Disposable disposable)
    {
        compositeDisposable.add(disposable);
    }

    void validateUserCredentials(String username , String password)
    {
        addDisposable(loginUseCase.validateCredentials(username , password).
                subscribeOn(ioScheduler).
                observeOn(mainScheduler).
                doOnSubscribe(loading -> loadingStatus.setValue(true)).
                doAfterTerminate(() -> loadingStatus.setValue(false)).
                subscribe(userResponse -> loginResponse.setValue(Response.success(userResponse)),
                        throwable -> loginResponse.setValue(Response.error(throwable))));
    }
}
