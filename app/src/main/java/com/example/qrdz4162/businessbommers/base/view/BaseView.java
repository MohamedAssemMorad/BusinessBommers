package com.example.qrdz4162.businessbommers.base.view;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public interface BaseView {

    void showProgressLoading();
    void hideProgressLoading();
    void showInlineError(String error);
    void showInlineConnectionError();
}
