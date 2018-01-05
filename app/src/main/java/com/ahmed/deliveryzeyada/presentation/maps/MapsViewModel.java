package com.ahmed.deliveryzeyada.presentation.maps;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ahmed.deliveryzeyada.contract.maps.MapsUseCase;
import com.ahmed.deliveryzeyada.contract.maps.model.PilotMapResponse;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.ResturantMapResponse;
import com.ahmed.deliveryzeyada.rx.RunOn;
import com.ahmed.deliveryzeyada.rx.SchedulerType;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.ahmed.deliveryzeyada.utils.Constants.USER_TYPE_PILOT;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public class MapsViewModel extends ViewModel
{
    private MapsUseCase mapsUseCase;

    private final MutableLiveData<List<ResturantMapResponse>> resturantResponseSuccessMutable = new MutableLiveData<>();
    private final MutableLiveData<Throwable> resturantResponseFailureMutable = new MutableLiveData<>();

    private final MutableLiveData<PilotMapResponse> pilotResponseSuccessMutable = new MutableLiveData<>();
    private final MutableLiveData<Throwable> pilotResponseFailureMutable = new MutableLiveData<>();

    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();

    private Scheduler ioScheduler , mainScheduler;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MapsViewModel(MapsUseCase mapsUseCase, @RunOn(SchedulerType.IO) Scheduler ioScheduler,
                         @RunOn(SchedulerType.UI) Scheduler mainScheduler)
    {
        this.mapsUseCase = mapsUseCase;
        this.ioScheduler = ioScheduler;
        this.mainScheduler = mainScheduler;
    }


    public MutableLiveData<List<ResturantMapResponse>> getResturantResponseSuccessMutable()
    {
        return resturantResponseSuccessMutable;
    }

    public MutableLiveData<Throwable> getResturantResponseFailureMutable()
    {
        return resturantResponseFailureMutable;
    }

    public MutableLiveData<PilotMapResponse> getPilotResponseSuccessMutable()
    {
        return pilotResponseSuccessMutable;
    }

    public MutableLiveData<Throwable> getPilotResponseFailureMutable()
    {
        return pilotResponseFailureMutable;
    }

    public MutableLiveData<Boolean> getLoadingStatus()
    {
        return loadingStatus;
    }

    void getResturants(String userType)
    {
        if(userType.equalsIgnoreCase(USER_TYPE_PILOT))
        {
            addDisposable(mapsUseCase.getResturants().
                    subscribeOn(ioScheduler).
                    observeOn(mainScheduler).
                    doOnSubscribe(loading -> loadingStatus.setValue(true)).
                    doAfterTerminate(() -> loadingStatus.setValue(false)).
                    subscribe(this::resturantsSuccess , this::resturantsFailure));
        }
    }


    private void resturantsSuccess(List<ResturantMapResponse> resturantMapResponse)
    {
        resturantResponseSuccessMutable.setValue(resturantMapResponse);
    }

    private void resturantsFailure(Throwable throwable)
    {
        resturantResponseFailureMutable.setValue(throwable);
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
}
