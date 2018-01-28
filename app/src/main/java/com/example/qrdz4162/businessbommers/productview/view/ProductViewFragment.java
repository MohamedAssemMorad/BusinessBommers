package com.example.qrdz4162.businessbommers.productview.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.base.injector.Injection;
import com.example.qrdz4162.businessbommers.base.view.BaseFragment;
import com.example.qrdz4162.businessbommers.productview.model.entitiy.ProductDetails;
import com.example.qrdz4162.businessbommers.productview.presenter.ProductDetailsPresenter;
import com.example.qrdz4162.businessbommers.productview.presenter.ProductDetailsPresenterImp;
import com.example.qrdz4162.businessbommers.utils.DialogUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductViewFragment extends BaseFragment implements ProductView{

    @BindView(R.id.imageView_product_detail_thumbnail)
    ImageView productDetailImage;

    @BindView(R.id.textView_product_detail_name)
    TextView productDetailName;

    @BindView(R.id.textView_product_detail_code)
    TextView productDetailCode;

    @BindView(R.id.textView_product_detail_barcode)
    TextView productDetailBarcode;

    @BindView(R.id.progressBarProductView)
    ProgressBar mProgressBar;

    @BindView(R.id.v_reload)
    View mReloadView;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @BindView(R.id.fab_product_list)
    FloatingActionButton productListButton;


    private ProductDetailsPresenter productDetailsPresenter;
    private String productCode;

    public ProductViewFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_t, container, false);
        ButterKnife.bind(this, rootView);


        productListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        if (getArguments().containsKey("product_code")) {
            productCode = getArguments().getString("product_code");
        }
        initializeProductPresenter();
    }

    private void initializeProductPresenter() {
        productDetailsPresenter = new ProductDetailsPresenterImp(this, Injection.provideProductViewRepository());
        productDetailsPresenter.loadProductDetailsData(productCode);
    }

    @Override
    public void showProgressLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showInlineError(String error) {
        DialogUtils.getSnackBar(getView(), error, null, null).show();
    }

    @Override
    public void showInlineConnectionError() {

        mReloadView.setVisibility(View.VISIBLE);
        mReloadView.findViewById(R.id.rl_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReloadView.setVisibility(View.GONE);
                productDetailsPresenter.loadProductDetailsData(productCode);
            }
        });
    }


    @Override
    public void loadProductDetails(ProductDetails productDetails) {

        productDetailName.setText(productDetails.getName());
        productDetailCode.setText(productDetails.getCode());
        productDetailBarcode.setText(productDetails.getBarcode());
        Picasso.with(getActivity().getApplicationContext()).
                load(BusinessBommersApp.BASE_IMAGE_URL + productDetails.getImages().get(0).getPath())
                .error(R.drawable.ic_product_placeholder)
                .fit().into(productDetailImage);
        ratingBar.setRating(Float.parseFloat(productDetails.getAverageRating()+""));
    }

    @Override
    public void onResume() {
        super.onResume();
        productDetailsPresenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        productDetailsPresenter.onViewDetached();
    }
}
