package com.example.qrdz4162.businessbommers.products.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.base.injector.Injection;
import com.example.qrdz4162.businessbommers.base.view.BaseFragment;
import com.example.qrdz4162.businessbommers.products.adapter.ProductListAdapter;
import com.example.qrdz4162.businessbommers.products.model.ProductListRepository;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.example.qrdz4162.businessbommers.products.model.repo.ProductListRemoteDataSource;
import com.example.qrdz4162.businessbommers.products.presenter.MainPresenter;
import com.example.qrdz4162.businessbommers.products.presenter.MainPresenterImpl;
import com.example.qrdz4162.businessbommers.productview.ProductViewActivity;
import com.example.qrdz4162.businessbommers.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class MainFragment extends BaseFragment implements MainView , ProductListAdapter.ProductClickListener {

    @BindView(R.id.products_recycler_view)
    RecyclerView productsRecyclerView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.v_reload)
    View mReloadView;

    // declare product list components
    private RecyclerView.Adapter productListAdapter;
    private RecyclerView.LayoutManager productLayoutManager;
    private ArrayList<ProductItem> productList;

    // declare main presenter
    private MainPresenter mainPresenter;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // initialize recyclerView
        productsRecyclerView.setHasFixedSize(true);
        productLayoutManager = new LinearLayoutManager(getActivity());
        productsRecyclerView.setLayoutManager(productLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(productsRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        productsRecyclerView.addItemDecoration(dividerItemDecoration);

        productList = new ArrayList<>();
        productListAdapter = new ProductListAdapter(productList,this);
        productsRecyclerView.setAdapter(productListAdapter);

        initializePresenter();
    }

    private void initializePresenter() {

        mainPresenter = new MainPresenterImpl(this, Injection.provideProductListRepository());
        mainPresenter.loadProducts();
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
                mainPresenter.loadProducts();
            }
        });
    }


    @Override
    public void loadProductList(List<ProductItem> productItems) {

        productList.clear();
        productListAdapter.notifyDataSetChanged();

        productList.addAll(productItems);
        productListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductClick(int position, View v) {
        Intent productViewIntent = new Intent(getActivity(), ProductViewActivity.class);
        productViewIntent.putExtra("product_code",productList.get(position).getCode());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                        v,   // Starting view
                        getString(R.string.transition_name));
        ActivityCompat.startActivity(getActivity(), productViewIntent, options.toBundle());
//        startActivity(productViewIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.onViewDetached();
    }
}
