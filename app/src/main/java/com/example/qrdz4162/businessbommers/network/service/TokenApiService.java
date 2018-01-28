package com.example.qrdz4162.businessbommers.network.service;

import com.example.qrdz4162.businessbommers.base.model.entity.TokenResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface TokenApiService {

    @FormUrlEncoded
    @POST("oauth/v2/token")
    Observable<TokenResponse> getToken (@Field("client_id") String client_id,
                                        @Field("client_secret") String client_secret,
                                        @Field("grant_type") String grant_type,
                                        @Field("username") String user_name,
                                        @Field("password") String password);
}
