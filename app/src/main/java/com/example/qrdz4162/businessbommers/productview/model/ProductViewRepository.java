package com.example.qrdz4162.businessbommers.productview.model;

import com.example.qrdz4162.businessbommers.products.model.ProductListRepository;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;
import com.example.qrdz4162.businessbommers.productview.model.repo.ProductViewDataSource;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 1/27/2018.
 */

public class ProductViewRepository implements ProductViewDataSource{

    private static ProductViewRepository INSTANCE = null;
    private final ProductViewDataSource productViewRemoteDataSource;

    // Prevent direct instantiation.
    private ProductViewRepository(ProductViewDataSource productViewRemoteDataSource) {
        this.productViewRemoteDataSource = productViewRemoteDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param productViewRemoteDataSource the backend data source
     * @return the {@link ProductListRepository} instance
     */
    public static ProductViewRepository getInstance(ProductViewDataSource productViewRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ProductViewRepository(productViewRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<ProductDetails> getSingleProduct(String code) {
        return productViewRemoteDataSource.getSingleProduct(code);
    }
}
