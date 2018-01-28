package com.example.qrdz4162.businessbommers.productview.model.repo;

import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.application.BusinessBommersSharedPref;
import com.example.qrdz4162.businessbommers.network.service.ProductApiService;
import com.example.qrdz4162.businessbommers.network.RetrofitClient;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 1/27/2018.
 */

public class ProductViewRemoteDataSource implements ProductViewDataSource {

    private static ProductViewRemoteDataSource INSTANCE;
    private final ProductApiService productViewApiService;

    // Prevent direct instantiation.
    private ProductViewRemoteDataSource() {
        productViewApiService = RetrofitClient.getRetrofitClient().create(ProductApiService.class);
    }

    public static ProductViewRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductViewRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<ProductDetails> getSingleProduct(String code) {
        String accessToken = BusinessBommersSharedPref.getAccessToken(BusinessBommersApp.getInstance());
        return productViewApiService.getSingleProduct(accessToken, code);
    }


}
