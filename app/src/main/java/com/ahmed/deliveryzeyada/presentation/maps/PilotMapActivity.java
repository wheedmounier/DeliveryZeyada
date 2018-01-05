package com.ahmed.deliveryzeyada.presentation.maps;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.data.Remote.api.maps.ResturantMapResponse;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class PilotMapActivity extends AppCompatActivity
{
    @Inject
    MapsViewModelFactory mapsViewModelFactory;

    MapsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilot_map);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this , mapsViewModelFactory).get(MapsViewModel.class);
        viewModel.getResturantResponseSuccessMutable().observe(this , this::showResturantsList);

        viewModel.getResturants("pilot");
    }

    private void showResturantsList(List<ResturantMapResponse> resturantMapResponse)
    {
        Toast.makeText(this , resturantMapResponse.get(0).getName() , Toast.LENGTH_SHORT).show();
    }
}
