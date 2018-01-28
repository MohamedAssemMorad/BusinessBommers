package com.example.qrdz4162.businessbommers.base.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class TokenResponse {

    @SerializedName("access_token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
