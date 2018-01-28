package com.example.qrdz4162.businessbommers.base.injector;

import com.example.qrdz4162.businessbommers.products.model.ProductListRepository;
import com.example.qrdz4162.businessbommers.products.model.repo.ProductListRemoteDataSource;
import com.example.qrdz4162.businessbommers.productview.model.ProductViewRepository;
import com.example.qrdz4162.businessbommers.productview.model.repo.ProductViewRemoteDataSource;

public class Injection {

    /**
     * Enables injection of mock implementations for
     * {@link ProductListRepository} at compile time. This is useful to isolate the dependencies.
     */
    public static ProductListRepository provideProductListRepository() {
        return ProductListRepository.getInstance(ProductListRemoteDataSource.getInstance());
    }

    /**
     * Enables injection of mock implementations for
     * {@link ProductViewRepository} at compile time. This is useful to isolate the dependencies.
     */
    public static ProductViewRepository provideProductViewRepository() {
        return ProductViewRepository.getInstance(ProductViewRemoteDataSource.getInstance());
    }
}
