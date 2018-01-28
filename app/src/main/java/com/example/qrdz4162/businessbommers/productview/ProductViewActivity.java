package com.example.qrdz4162.businessbommers.productview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.base.view.BaseActivity;
import com.example.qrdz4162.businessbommers.productview.view.ProductViewFragment;
import com.example.qrdz4162.businessbommers.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewActivity extends BaseActivity {

    private String productCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            productCode = getIntent().getExtras().getString("product_code");
        }

        ProductViewFragment productViewFragment = new ProductViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("product_code", productCode);
        productViewFragment.setArguments(bundle);

        FragmentUtils.replaceFragment(this, productViewFragment, R.id.fragment_product_view_container,
                false, "");

    }


}
