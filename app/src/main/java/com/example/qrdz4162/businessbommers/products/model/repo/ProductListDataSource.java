package com.example.qrdz4162.businessbommers.products.model.repo;

import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface ProductListDataSource {
    Observable<List<ProductItem>> getProducts();
}
