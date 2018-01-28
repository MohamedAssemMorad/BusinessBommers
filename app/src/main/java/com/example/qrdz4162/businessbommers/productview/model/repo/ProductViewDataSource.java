package com.example.qrdz4162.businessbommers.productview.model.repo;


import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;

import io.reactivex.Observable;

/**
 * Created by qrdz4162 on 1/27/2018.
 */

public interface ProductViewDataSource {

    Observable<ProductDetails> getSingleProduct(String code);
}
