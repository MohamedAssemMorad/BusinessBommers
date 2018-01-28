package com.example.qrdz4162.businessbommers.splash;

import android.content.Intent;
import android.os.Bundle;;
import android.view.Window;
import android.view.WindowManager;

import com.example.qrdz4162.businessbommers.R;
import com.example.qrdz4162.businessbommers.base.view.BaseActivity;
import com.example.qrdz4162.businessbommers.products.MainActivity;

/**
 * Created by qrdz4162 on 1/28/2018.
 */

public class SplashScreen extends BaseActivity {

    protected boolean _active = true;
    protected int _splashTime = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {

                } finally {
                    finish();
                    Intent startIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(startIntent);
                }
            }
        };
        splashTread.start();
    }
}