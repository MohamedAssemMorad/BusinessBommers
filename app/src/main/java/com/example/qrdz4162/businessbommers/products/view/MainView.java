package com.example.qrdz4162.businessbommers.products.view;

import com.example.qrdz4162.businessbommers.base.view.BaseView;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;

import java.util.List;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface MainView extends BaseView {
    void loadProductList(List<ProductItem> products);
}
