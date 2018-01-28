package com.example.qrdz4162.businessbommers.base.model;

import com.example.qrdz4162.businessbommers.base.model.entity.TokenResponse;
import com.example.qrdz4162.businessbommers.network.RetrofitClient;
import com.example.qrdz4162.businessbommers.network.service.TokenApiService;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class TokenAccessRepository {

    private static TokenAccessRepository INSTANCE;
    private final TokenApiService tokenApiService;

    // Prevent direct instantiation.
    private TokenAccessRepository() {
        tokenApiService = RetrofitClient.getRetrofitClient().create(TokenApiService.class);
    }

    public static TokenAccessRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TokenAccessRepository();
        }
        return INSTANCE;
    }

    public Observable<TokenResponse> getValidAccessToken() {
        
        return tokenApiService.getToken("46v3htox13uookw4o8c8gs44oggocgos88804oggggkwss8o04",
                "4jm5k8h9vxmokkssw4wkcsgs0cws0kow0w48s8gc80cwc404g0",
                "password",
                "api@example.com",
                "api");
    }
}
