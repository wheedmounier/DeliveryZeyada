package com.ahmed.deliveryzeyada.contract.maps;

import com.ahmed.deliveryzeyada.data.Remote.api.maps.ResturantMapResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public interface MapsUseCase
{
    Single<List<ResturantMapResponse>> getResturants();
}
