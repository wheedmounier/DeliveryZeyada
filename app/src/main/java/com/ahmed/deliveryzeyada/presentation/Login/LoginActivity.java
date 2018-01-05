package com.ahmed.deliveryzeyada.presentation.Login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ahmed.deliveryzeyada.DeliveryApp;
import com.ahmed.deliveryzeyada.R;
import com.ahmed.deliveryzeyada.data.Remote.api.login.model.LoginResponse;
import com.ahmed.deliveryzeyada.presentation.maps.PilotMapActivity;
import com.ahmed.deliveryzeyada.presentation.maps.ResturantsMapActivity;
import com.google.firebase.FirebaseApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

import static com.ahmed.deliveryzeyada.utils.Constants.LOGIN_PASSWORD;
import static com.ahmed.deliveryzeyada.utils.SharedPrefsConst.ACCESS_TOKEN;
import static com.ahmed.deliveryzeyada.utils.SharedPrefsConst.ACCESS_TOKEN_TYPE;
import static com.ahmed.deliveryzeyada.utils.SharedPrefsConst.PREFS_NAME;

public class LoginActivity extends AppCompatActivity
{

    @Inject
    LoginViewModelFactory loginViewModelFactory;

    @BindView(R.id.login_email_et)
    EditText email;

    @BindView(R.id.login_pass_et)
    EditText password;

    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    private String userEmail, userPass;

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
        viewModel.getLoginSuccessMutable().observe(this , this::loginSuccess);
        viewModel.getLoginFailureMutable().observe(this , this::loginFailure);

        viewModel.getCheckPilotSuccessMutable().observe(this , this::pilotSuccess);
        viewModel.getCheckResturantSuccessMutable().observe(this , this::resturantSuccess);
        viewModel.getCheckUserFailureMutable().observe(this , this::checkUserFailure);
    }

    @OnClick(R.id.btn_login) void loginClicked()
    {
        userEmail = email.getText().toString();
        userPass = password.getText().toString();

        viewModel.validateUserCredentials(userEmail , userPass , LOGIN_PASSWORD , DeliveryApp.getFirebaseToken());
    }

    private void loginSuccess(LoginResponse loginResponse)
    {
        getSharedPreferences(PREFS_NAME , MODE_PRIVATE).edit().
                putString(ACCESS_TOKEN , loginResponse.getAccessToken()).
                putString(ACCESS_TOKEN_TYPE , loginResponse.getTokenType()).apply();

        viewModel.checkUser(userEmail , userPass);
    }

    private void loginFailure(Throwable throwable)
    {
        Toast.makeText(this , "User doesn't exist please contact the admin" , Toast.LENGTH_SHORT).show();
    }

    private void pilotSuccess(String pilot)
    {
        //startActivity(new Intent(this , PilotMapActivity.class));
    }

    private void resturantSuccess(String resturant)
    {

    }


    private void checkUserFailure(Throwable throwable)
    {

    }

    private void showHideLoading(boolean isLoading)
    {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}
