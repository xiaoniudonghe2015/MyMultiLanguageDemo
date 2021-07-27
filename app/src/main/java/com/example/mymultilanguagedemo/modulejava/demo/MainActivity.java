package com.example.mymultilanguagedemo.modulejava.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.mymultilanguagedemo.R;
import com.example.mymultilanguagedemo.modulejava.lib.OnChangeLanguageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MultiLanguageUtil.getInstance().setConfiguration();
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        EventBus.getDefault().register(this);
    }

    public void openSettingLanguage(View view) {
        startActivity(new Intent(this, SetLanguageActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(OnChangeLanguageEvent event) {
        Log.d("onchange", "ChangeLanguage");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
