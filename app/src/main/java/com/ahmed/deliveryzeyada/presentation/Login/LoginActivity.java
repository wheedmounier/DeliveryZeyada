package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.data.Remote.api.login.LoginResponse;
import com.ahmed.deliveryzeyada.presentation.viewModel.ViewModelResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity
{

    @Inject
    LoginViewModelFactory loginViewModelFactory;

    @BindView(R.id.mvvm_test)
    TextView mvvm_test;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this , loginViewModelFactory).get(LoginViewModel.class);
        viewModel.getLoadingStatus().observe(this , this::showHideLoading);
        viewModel.getLoginMutableResponse().observe(this , this::loginStatus);
        loginClicked();
    }

    void loginClicked()
    {
        viewModel.validateUserCredentials("Admin@QD.com" , "123456");
    }

    private void loginStatus(ViewModelResponse<LoginResponse> loginResponse)
    {
        switch (loginResponse.status) {
            case SUCCESS:
                //Success
                mvvm_test.setText("success");
                break;

            case ERROR:
                //failure
                mvvm_test.setText("failed");
                break;
        }
    }

    private void showHideLoading(boolean isLoading)
    {
        //loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
