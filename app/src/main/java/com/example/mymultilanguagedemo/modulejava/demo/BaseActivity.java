package com.example.mymultilanguagedemo.modulejava.demo;

import android.content.Context;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.mymultilanguagedemo.modulejava.lib.MultiLanguageUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }
}
