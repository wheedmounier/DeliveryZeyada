package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.data.Remote.Response;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import timber.log.Timber;

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
        viewModel.getLoginResponse().observe(this , this::loginStatus);
        loginClicked();
    }

    void loginClicked()
    {
        viewModel.validateUserCredentials("" , "");
    }

    private void loginStatus(Response response)
    {
        switch (response.status) {
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
