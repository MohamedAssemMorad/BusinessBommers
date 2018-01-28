package com.example.qrdz4162.businessbommers.network.service;

import com.example.qrdz4162.businessbommers.base.model.entity.TokenResponse;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductResponse;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface ProductApiService {

    @GET("v1/products/{code}")
    Observable<ProductDetails> getSingleProduct(@Header("Authorization") String token, @Path("code") String code);

    @GET("v1/products/")
    Observable<ProductResponse> getProductsList(@Header("Authorization") String token);
}
