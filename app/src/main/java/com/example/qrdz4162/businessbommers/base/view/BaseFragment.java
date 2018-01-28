package com.example.qrdz4162.businessbommers.base.view;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.qrdz4162.businessbommers.utils.KeyboardUtils;

/**
 * Created by qrdz4162 on 1/26/2018.
 */

public class BaseFragment extends Fragment{

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void showSoftKeyboard(View view) {
        KeyboardUtils.showSoftKeyboard(getActivity(), view);
    }

    public void hideSoftKeyboard() {
        KeyboardUtils.hideSoftKeyboard(getActivity());
    }
}
