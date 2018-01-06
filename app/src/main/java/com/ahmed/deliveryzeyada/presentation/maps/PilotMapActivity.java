package com.ahmed.deliveryzeyada.presentation.maps;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.ResturantMapResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

import static com.ahmed.deliveryzeyada.utils.Constants.USER_TYPE_PILOT;

public class PilotMapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener
{
    @Inject
    MapsViewModelFactory mapsViewModelFactory;

    MapsViewModel viewModel;
    private GoogleMap googleMap;

    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilot_map);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this , mapsViewModelFactory).get(MapsViewModel.class);
        viewModel.getResturantResponseSuccessMutable().observe(this , this::showResturantsList);
        viewModel.getDrawMapMutable().observe(this , this::drawSuccess);

        viewModel.getResturants(USER_TYPE_PILOT);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.pilot_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        this.googleMap = googleMap;
    }

    private void drawSuccess(GoogleMap googleMap)
    {
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
    }

    private void showResturantsList(List<ResturantMapResponse> resturantMapResponse)
    {
        if(googleMap != null)
        {
            viewModel.drawOnMap(googleMap , resturantMapResponse);
        }
    }

    @Override
    public void onPolygonClick(Polygon polygon)
    {

    }

    @Override
    public void onPolylineClick(Polyline polyline)
    {

    }
}
