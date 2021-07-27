package com.example.mymultilanguagedemo.kotlinmodule.demo

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import com.example.mymultilanguagedemo.kotlinmodule.lib.MultiLanguageUtil

/**
 * Created by lx on 17-10-26.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiLanguageUtil.init(this)
        //        MultiLanguageUtil.getInstance().setConfiguration();
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {
//                MultiLanguageUtil.getInstance().setConfiguration();
            }

            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //        MultiLanguageUtil.getInstance().setConfiguration();
    }
}