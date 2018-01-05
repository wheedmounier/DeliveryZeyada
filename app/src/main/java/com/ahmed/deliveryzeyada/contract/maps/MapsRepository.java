package com.ahmed.deliveryzeyada.contract.maps;

import com.ahmed.deliveryzeyada.data.Remote.api.maps.MapsService;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.ResturantMapResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public class MapsRepository implements MapsUseCase
{
    private MapsService service;

    @Inject
    public MapsRepository(MapsService service)
    {
        this.service = service;
    }

    @Override
    public Single<List<ResturantMapResponse>> getResturants()
    {
        return service.getResturants();
    }
}
