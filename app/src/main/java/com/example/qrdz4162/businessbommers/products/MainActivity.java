package com.example.qrdz4162.businessbommers.products;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.base.view.BaseActivity;
import com.example.qrdz4162.businessbommers.products.view.MainFragment;
import com.example.qrdz4162.businessbommers.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FragmentUtils.replaceFragment(this, new MainFragment(), R.id.fragment_main_container,false, "");
    }
}
