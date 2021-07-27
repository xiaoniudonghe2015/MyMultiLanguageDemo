package com.example.mymultilanguagedemo.kotlinmodule.demo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymultilanguagedemo.kotlinmodule.lib.MultiLanguageUtil

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase))
    }
}