package com.example.mymultilanguagedemo.kotlinmodule.demo

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import com.example.mymultilanguagedemo.R
import com.example.mymultilanguagedemo.kotlinmodule.lib.OnChangeLanguageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        MultiLanguageUtil.getInstance().setConfiguration();
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_name)
        EventBus.getDefault().register(this)
    }


    fun openSettingLanguage(view: View?) {
        startActivity(Intent(this, SetLanguageActivity::class.java))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onChangeLanguageEvent(event: OnChangeLanguageEvent?) {
        Log.d("onchange", "ChangeLanguage")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Process.killProcess(Process.myPid())
    }
}