package com.example.qrdz4162.businessbommers.products.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.application.BusinessBommersApp;
import com.example.qrdz4162.businessbommers.products.model.entitiy.ProductItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.DataObjectHolder> {

    private ArrayList<ProductItem> mDataSet;
    private Context context;
    private static ProductClickListener mProductClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView_product_thumbnail)
        ImageView productImageView;

        @BindView(R.id.textView_product_name)
        TextView productNameTextView;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            //adding listener...
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            mProductClickListener.onProductClick(getAdapterPosition(), v);
        }
    }

    public ProductListAdapter(ArrayList<ProductItem> mDataSet, ProductClickListener mProductClickListener) {
        this.mDataSet = mDataSet;
        this.mProductClickListener = mProductClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        context = parent.getContext();

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        ProductItem productItem = mDataSet.get(position);

        holder.productNameTextView.setText(productItem.getName());
        Picasso.with(context)
                .load(BusinessBommersApp.BASE_IMAGE_URL + productItem.getImages().get(0).getPath())
                .error(R.drawable.ic_product_placeholder)
                .fit()
                .into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface ProductClickListener {
        void onProductClick(int position, View v);
    }
}

