package com.example.spotpoc.ui;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.spotpoc.R;
import com.example.spotpoc.networking.ApiClient;
import com.example.spotpoc.networking.ApiService;
import com.example.spotpoc.networking.model.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import okhttp3.ResponseBody;

public class BaseActivity extends AppCompatActivity {
    private static ApiClient mApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ApiClient getApi() {
        if (mApi == null) {
            mApi = ApiService.getClient().create(ApiClient.class);
        }
        return mApi;
    }

    public void handleError(ResponseBody responseBody) {
        String message = null;
        if (responseBody != null) {
            try {
                ErrorResponse errorResponse = new Gson().fromJson(responseBody.charStream(), ErrorResponse.class);
                message = errorResponse.error;
            } catch (JsonSyntaxException e) {
            } catch (JsonIOException e) {
            }
        }

        message = TextUtils.isEmpty(message) ? getString(R.string.msg_unknown) : message;
        showErrorDialog(message);
    }
    public void showErrorDialog(String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.error))
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                })
                .show();
    }


    public void handleError(Throwable throwable) {
        showErrorDialog(getString(R.string.msg_unknown));
    }

    public void handleUnknownError() {
        showErrorDialog(getString(R.string.msg_unknown));
    }

}
