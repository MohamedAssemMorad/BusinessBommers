package com.example.qrdz4162.businessbommers.products.model;

import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.example.qrdz4162.businessbommers.products.model.repo.ProductListDataSource;

import java.util.List;

import io.reactivex.Observable;

public class ProductListRepository implements ProductListDataSource {

    private static ProductListRepository INSTANCE = null;
    private final ProductListDataSource productListRemoteDataSource;

    // Prevent direct instantiation.
    private ProductListRepository(ProductListDataSource productListRemoteDataSource) {
        this.productListRemoteDataSource = productListRemoteDataSource;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param productListRemoteDataSource the backend data source
     * @return the {@link ProductListRepository} instance
     */
    public static ProductListRepository getInstance(ProductListDataSource productListRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ProductListRepository(productListRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<ProductItem>> getProducts() {
        return productListRemoteDataSource.getProducts();
    }


}