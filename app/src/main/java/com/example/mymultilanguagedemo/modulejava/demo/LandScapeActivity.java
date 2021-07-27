package com.example.mymultilanguagedemo.modulejava.demo;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.mymultilanguagedemo.R;

/**
 * 横屏会重置语言
 */
public class LandScapeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_scape);
        setTitle(R.string.app_name);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
