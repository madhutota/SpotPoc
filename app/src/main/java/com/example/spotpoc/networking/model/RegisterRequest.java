package com.example.spotpoc.networking.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    @SerializedName("password_confirmation")
    public String confirmPassword;
    public String mobile;
    public String gender;


}
