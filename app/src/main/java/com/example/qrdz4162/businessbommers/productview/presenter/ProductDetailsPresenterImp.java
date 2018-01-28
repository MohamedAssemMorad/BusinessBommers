package com.example.qrdz4162.businessbommers.productview.presenter;

import android.view.View;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.application.BusinessBommersSharedPref;
import com.example.qrdz4162.businessbommers.base.model.TokenAccessRepository;
import com.example.qrdz4162.businessbommers.base.model.entity.TokenResponse;
import com.example.qrdz4162.businessbommers.base.view.BaseView;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.example.qrdz4162.businessbommers.productview.model.ProductViewRepository;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;
import com.example.qrdz4162.businessbommers.productview.view.ProductView;
import com.example.qrdz4162.businessbommers.utils.TextUtils;

import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by qrdz4162 on 1/27/2018.
 */

public class ProductDetailsPresenterImp implements ProductDetailsPresenter {

    private ProductView productView;
    private ProductViewRepository productViewRepository;
    private CompositeDisposable mCompositeDisposable;
    private boolean isViewAttached;

    public ProductDetailsPresenterImp(ProductView productView, ProductViewRepository productViewRepository){
        this.productView = productView;
        this.productViewRepository = productViewRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadProductDetailsData(final String code) {

        if (isViewAttached) {
            productView.showProgressLoading();
        }

        Disposable disposable = productViewRepository.getSingleProduct(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ProductDetails>() {
                    @Override
                    public void onNext(@NonNull  ProductDetails productDetails) {
                        if (isViewAttached) {
                            productView.loadProductDetails(productDetails);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                        // Handle No Internet Connection
                        if (throwable instanceof UnknownHostException) {
                            if (isViewAttached) {
                                productView.hideProgressLoading();
                                productView.showInlineConnectionError();
                            }
                        }
                        // Handle HTTP Exceptions
                        else if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            switch (exception.code()) {
                                case 401:
                                    // Handle code 401 : Unauthorized
                                    getAccessTokenAndRetry(code);
                                    break;
                                case 500:
                                    // Handle code 500
                                    break;
                                default:
                                    break;
                            }
                        }
                        // Handle Unknown Exception
                        else {
                            if (isViewAttached) {
                                productView.hideProgressLoading();
                                productView.showInlineError(TextUtils.getString(R.string.unknown_error));
                            }
                        }

                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached) {
                            productView.hideProgressLoading();
                        }
                    }
                });

        mCompositeDisposable.add(disposable);

    }

    private void getAccessTokenAndRetry(final String code) {

        Disposable disposable = TokenAccessRepository.getInstance().getValidAccessToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TokenResponse>() {
                    @Override
                    public void onNext(@NonNull TokenResponse tokenResponse) {
                        //save token to sharedPref
                        BusinessBommersSharedPref.setAccessToken(BusinessBommersApp.getInstance(),
                                "Bearer "+tokenResponse.getToken());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        //retry load
                        loadProductDetailsData(code);
                    }
                });

        mCompositeDisposable.add(disposable);
    }


    @Override
    public void onViewAttached(BaseView view) {
        isViewAttached = true;
    }

    @Override
    public void onViewDetached() {
        isViewAttached = false;
        mCompositeDisposable.clear();
    }


}
