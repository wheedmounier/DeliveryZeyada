package com.ahmed.deliveryzeyada.presentation.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.presentation.Login.LoginActivity;

public class SplashActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this , LoginActivity.class));
        finish();
    }
}
