package com.example.mymultilanguagedemo.modulejava.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymultilanguagedemo.R;

/**
 * 测试语言是否被重置
 */
public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        setTitle(R.string.app_name);

    }
}
