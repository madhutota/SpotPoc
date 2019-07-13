package com.example.spotpoc.networking;

import com.example.spotpoc.networking.model.LoginModel;
import com.example.spotpoc.networking.model.LoginRequest;
import com.example.spotpoc.networking.model.RegisterRequest;
import com.example.spotpoc.networking.model.RegisterResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {
    @POST(ApiUrls.LOGIN_URL)
    Call<LoginModel> login(@Body LoginRequest loginRequest);

    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);


}