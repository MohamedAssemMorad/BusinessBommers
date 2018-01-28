package com.example.qrdz4162.businessbommers.productview.view;

import com.example.qrdz4162.businessbommers.base.view.BaseView;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface ProductView extends BaseView{

    void loadProductDetails(ProductDetails productDetails);
}
