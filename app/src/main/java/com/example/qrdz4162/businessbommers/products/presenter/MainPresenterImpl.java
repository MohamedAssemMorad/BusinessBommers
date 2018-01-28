package com.example.qrdz4162.businessbommers.products.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.application.BusinessBommersSharedPref;
import com.example.qrdz4162.businessbommers.base.model.TokenAccessRepository;
import com.example.qrdz4162.businessbommers.base.model.entity.TokenResponse;
import com.example.qrdz4162.businessbommers.base.view.BaseView;
import com.example.qrdz4162.businessbommers.products.model.ProductListRepository;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.example.qrdz4162.businessbommers.products.view.MainView;
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
 * Created by qrdz4162 on 1/26/2018.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private ProductListRepository productListRepository;
    private CompositeDisposable mCompositeDisposable;
    private boolean isViewAttached;

    public MainPresenterImpl(MainView mainView, ProductListRepository productListRepository) {
        this.mainView = mainView;
        this.productListRepository = productListRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadProducts() {

        mainView.showProgressLoading();


        Disposable disposable = productListRepository.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<ProductItem>>() {
                    @Override
                    public void onNext(@NonNull List<ProductItem> productItems) {
                        if (isViewAttached) {
                            mainView.loadProductList(productItems);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                        // Handle No Internet Connection
                        if (throwable instanceof UnknownHostException) {
                            if (isViewAttached) {
                                mainView.hideProgressLoading();
                                mainView.showInlineConnectionError();
                            }
                        }
                        // Handle HTTP Exceptions
                        else if (throwable instanceof HttpException) {
                            HttpException exception = (HttpException) throwable;
                            switch (exception.code()) {
                                case 401:
                                    // Handle code 401 : Unauthorized
                                    getAccessTokenAndRetry();
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
                                mainView.hideProgressLoading();
                                mainView.showInlineError(TextUtils.getString(R.string.unknown_error));
                            }
                        }


                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached) {
                            mainView.hideProgressLoading();
                        }
                    }
                });

        mCompositeDisposable.add(disposable);

    }

    private void getAccessTokenAndRetry() {

        Disposable disposable = TokenAccessRepository.getInstance().getValidAccessToken()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TokenResponse>() {
                    @Override
                    public void onNext(@NonNull TokenResponse tokenResponse) {
                        //save token to sharedPref
                        BusinessBommersSharedPref.setAccessToken(BusinessBommersApp.getInstance(),
                                "Bearer " + tokenResponse.getToken());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        //retry load products
                        loadProducts();
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
