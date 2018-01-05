package com.ahmed.deliveryzeyada.data.Remote.api.maps;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

import static com.ahmed.deliveryzeyada.utils.Constants.RESTURANTS_MAP_API;

/**
 * Created by Ahmed Kamal on 05/01/2018.
 */

public interface MapsService
{
    @GET(RESTURANTS_MAP_API)
    Single<List<ResturantMapResponse>> getResturants();
}
