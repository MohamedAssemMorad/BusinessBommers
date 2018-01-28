package com.example.qrdz4162.businessbommers.products.model.repo;

import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.application.BusinessBommersSharedPref;
import com.example.qrdz4162.businessbommers.network.service.ProductApiService;
import com.example.qrdz4162.businessbommers.network.RetrofitClient;
import com.example.qrdz4162.businessbommers.products.model.entitiy.Embedded;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Mohamed Elgendy.
 */

public class ProductListRemoteDataSource implements ProductListDataSource {

    private static ProductListRemoteDataSource INSTANCE;
    private final ProductApiService productApiService;

    // Prevent direct instantiation.
    private ProductListRemoteDataSource() {
        productApiService = RetrofitClient.getRetrofitClient().create(ProductApiService.class);
    }

    public static ProductListRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductListRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<ProductItem>> getProducts() {

        String accessToken = BusinessBommersSharedPref.getAccessToken(BusinessBommersApp.getInstance());
        return productApiService.getProductsList(accessToken)
                .map(new Function<ProductResponse, Embedded>() {
                    @Override
                    public Embedded apply(@NonNull ProductResponse productResponse) throws Exception {
                        return productResponse.getEmbedded();
                    }
                })
                .map(new Function<Embedded, List<ProductItem>>() {
                    @Override
                    public List<ProductItem> apply(@NonNull Embedded embedded) throws Exception {
                        return embedded.getItems();
                    }
                });

    }
}


