package com.example.mymultilanguagedemo.kotlinmodule.lib

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.util.Log
import com.example.mymultilanguagedemo.R
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * 多语言切换的帮助类
 * http://blog.csdn.net/finddreams
 */
class MultiLanguageUtil private constructor(private val mContext: Context) {
    /**
     * 设置语言
     */
    fun setConfiguration() {
        val targetLocale = languageLocale
        val configuration = mContext.resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale)
        } else {
            configuration.locale = targetLocale
        }
        val resources = mContext.resources
        val dm = resources.displayMetrics
        resources.updateConfiguration(configuration, dm) //语言更换生效的代码!
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回简体中文
     *
     * @return
     */
    private val languageLocale: Locale
        private get() {
            val languageType: Int = CommSharedUtil.Companion.getInstance(mContext)!!
                .getInt(SAVE_LANGUAGE, 0)
            if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
                return sysLocale
            } else if (languageType == LanguageType.LANGUAGE_EN) {
                return Locale.ENGLISH
            } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
                return Locale.SIMPLIFIED_CHINESE
            } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
                return Locale.TRADITIONAL_CHINESE
            }
            getSystemLanguage(sysLocale)
            Log.e(TAG, "getLanguageLocale$languageType$languageType")
            return Locale.SIMPLIFIED_CHINESE
        }

    private fun getSystemLanguage(locale: Locale): String {
        return locale.language + "_" + locale.country
    }

    //以上获取方式需要特殊处理一下
    val sysLocale: Locale
        get() {
            val locale: Locale
            locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList.getDefault()[0]
            } else {
                Locale.getDefault()
            }
            return locale
        }

    /**
     * 更新语言
     *
     * @param languageType
     */
    fun updateLanguage(languageType: Int) {
        CommSharedUtil.Companion.getInstance(mContext)!!
            .putInt(SAVE_LANGUAGE, languageType)
        getInstance()!!
            .setConfiguration()
        EventBus.getDefault().post(OnChangeLanguageEvent(languageType))
    }

    fun getLanguageName(context: Context): String {
        val languageType: Int = CommSharedUtil.Companion.getInstance(context)!!
            .getInt(SAVE_LANGUAGE, LanguageType.LANGUAGE_FOLLOW_SYSTEM)
        if (languageType == LanguageType.LANGUAGE_EN) {
            return mContext.getString(R.string.setting_language_english)
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            return mContext.getString(R.string.setting_simplified_chinese)
        } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            return mContext.getString(R.string.setting_traditional_chinese)
        }
        return mContext.getString(R.string.setting_language_auto)
    }

    /**
     * 获取到用户保存的语言类型
     * @return
     */
    val languageType: Int
        get() {
            val languageType: Int = CommSharedUtil.Companion.getInstance(mContext)!!
                .getInt(SAVE_LANGUAGE, LanguageType.LANGUAGE_FOLLOW_SYSTEM)
            if (languageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
                return LanguageType.LANGUAGE_CHINESE_SIMPLIFIED
            } else if (languageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
                return LanguageType.LANGUAGE_CHINESE_TRADITIONAL
            } else if (languageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
                return LanguageType.LANGUAGE_FOLLOW_SYSTEM
            }
            Log.e(TAG, "getLanguageType$languageType")
            return languageType
        }

    companion object {
        private const val TAG = "MultiLanguageUtil"
        private var instance: MultiLanguageUtil? = null
        const val SAVE_LANGUAGE = "save_language"
        fun init(mContext: Context) {
            if (instance == null) {
                synchronized(MultiLanguageUtil::class.java) {
                    if (instance == null) {
                        instance = MultiLanguageUtil(mContext)
                    }
                }
            }
        }

        fun getInstance(): MultiLanguageUtil? {
            checkNotNull(instance) { "You must be init MultiLanguageUtil first" }
            return instance
        }

        fun attachBaseContext(context: Context): Context {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                createConfigurationResources(context)
            } else {
                getInstance()!!
                    .setConfiguration()
                context
            }
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun createConfigurationResources(context: Context): Context {
            val resources = context.resources
            val configuration = resources.configuration
            val locale = getInstance()!!.languageLocale
            configuration.setLocale(locale)
            return context.createConfigurationContext(configuration)
        }
    }
}