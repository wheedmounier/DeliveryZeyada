package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ahmed.deliveryzeyada.contract.login.LoginUseCase;
import com.ahmed.deliveryzeyada.data.Remote.api.login.model.LoginResponse;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.ahmed.deliveryzeyada.utils.Constants.USER_TYPE_PILOT;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */

public class LoginViewModel extends ViewModel
{
    private final LoginUseCase loginUseCase;
    private Scheduler ioScheduler , mainScheduler;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<LoginResponse> loginSuccessMutable = new MutableLiveData<>();
    private final MutableLiveData<Throwable> loginFailureMutable = new MutableLiveData<>();

    private final MutableLiveData<String> checkPilotSuccessMutable = new MutableLiveData<>();
    private final MutableLiveData<String> checkResturantSuccessMutable = new MutableLiveData<>();
    private final MutableLiveData<Throwable> checkUserFailureMutable = new MutableLiveData<>();

    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();

    @Inject
    LoginViewModel(LoginUseCase loginUseCase, @RunOn(SchedulerType.IO) Scheduler ioScheduler,
                   @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        this.loginUseCase = loginUseCase;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }

    MutableLiveData<LoginResponse> getLoginSuccessMutable()
    {
        return loginSuccessMutable;
    }
    MutableLiveData<Throwable> getLoginFailureMutable()
    {
        return loginFailureMutable;
    }
    MutableLiveData<Boolean> getLoadingStatus()
    {
        return loadingStatus;
    }

    MutableLiveData<String> getCheckPilotSuccessMutable() {return checkPilotSuccessMutable;}
    MutableLiveData<String> getCheckResturantSuccessMutable() {return checkResturantSuccessMutable;}

    MutableLiveData<Throwable> getCheckUserFailureMutable() {return checkUserFailureMutable;}

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

    void validateUserCredentials(String email , String password , String grantTyoe , String deviceId)
    {
        if(!email.isEmpty() && !password.isEmpty())
        {
            addDisposable(loginUseCase.validateCredentials(email ,password ,grantTyoe ,deviceId).
                    subscribeOn(ioScheduler).
                    observeOn(mainScheduler).
                    doOnSubscribe(loading -> loadingStatus.setValue(true)).
                    doAfterTerminate(() -> loadingStatus.setValue(false)).
                    subscribe(this::handleLoginSuccess, this::handleLoginFailure));
        }
        else
        {
            loginFailureMutable.setValue(new Throwable("Fields Cannot be empty"));
        }
    }

    void checkUser(String email , String password)
    {
        addDisposable(loginUseCase.checkUser(email , password).
                subscribeOn(ioScheduler).
                observeOn(mainScheduler).
                doOnSubscribe(loading -> loadingStatus.setValue(true)).
                doAfterTerminate(() -> loadingStatus.setValue(false)).
                subscribe(this::checkUserSuccess , this::checkUserFailure));
    }

    private void handleLoginSuccess(LoginResponse loginResponse)
    {
        loginSuccessMutable.setValue(loginResponse);
    }

    private void handleLoginFailure(Throwable throwable)
    {
        getLoginFailureMutable().setValue(throwable);
    }

    private void checkUserSuccess(String type)
    {
        if(type.equalsIgnoreCase(USER_TYPE_PILOT))
        {
            checkPilotSuccessMutable.setValue(type);
        }
        else
        {
            checkResturantSuccessMutable.setValue(type);
        }
    }

    private void checkUserFailure(Throwable throwable)
    {
        checkUserFailureMutable.setValue(throwable);
    }


}
