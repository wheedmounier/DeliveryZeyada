package com.ahmed.deliveryzeyada.injection.login;

import com.ahmed.deliveryzeyada.contract.login.LoginUseCase;
import com.ahmed.deliveryzeyada.contract.login.LoginRepository;
import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginService;
import com.ahmed.deliveryzeyada.presentation.Login.LoginViewModelFactory;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by Ahmed Kamal on 03/01/2018.
 */
@Module
public class LoginModule
{
    @Provides
    LoginViewModelFactory provideLoginViewModelFactory(LoginUseCase loginUseCase ,
          @RunOn(SchedulerType.IO) Scheduler ioScheduler ,@RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        return new LoginViewModelFactory(loginUseCase , ioScheduler , mainScheduler);
    }

    @Provides
    LoginUseCase provideLoginUseCase(LoginService loginService)
    {
        return new LoginRepository(loginService);
    }
}
