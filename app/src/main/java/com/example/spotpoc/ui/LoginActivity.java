package com.example.spotpoc.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotpoc.R;
import com.example.spotpoc.helper.AppPref;
import com.example.spotpoc.networking.model.LoginModel;
import com.example.spotpoc.networking.model.LoginRequest;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_password)
    EditText inputPassword;

    @BindView(R.id.loader)
    AVLoadingIndicatorView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    void onLoginClick() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), getString(R.string.msg_enter_credentials), Toast.LENGTH_LONG).show();
            return;
        }

        loader.setVisibility(View.VISIBLE);
        LoginRequest request = new LoginRequest();
        request.email = email;
        request.password = password;
        getApi().login(request).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                loader.setVisibility(View.INVISIBLE);
                if (!response.isSuccessful()) {
                    return;
                } else {
                    LoginModel loginModel = response.body();
                    if (loginModel != null && loginModel.getUser().getApi_token() != null)
                        AppPref.getInstance().saveAuthToken(loginModel.getUser().getApi_token());
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                loader.setVisibility(View.INVISIBLE);
            }
        });
    }

    @OnClick(R.id.btn_create_account)
    void onCreateAccountClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
