package com.example.qrdz4162.businessbommers.base.presenter;

import com.example.qrdz4162.businessbommers.base.view.BaseView;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface BasePresenter {

    void onViewAttached(BaseView view);
    void onViewDetached();
}
