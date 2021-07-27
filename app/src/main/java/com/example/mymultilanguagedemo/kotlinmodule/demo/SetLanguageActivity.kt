package com.example.mymultilanguagedemo.kotlinmodule.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.mymultilanguagedemo.R
import com.example.mymultilanguagedemo.kotlinmodule.lib.LanguageType
import com.example.mymultilanguagedemo.kotlinmodule.lib.MultiLanguageUtil

/**
 * 设置语言页面
 */
class SetLanguageActivity : BaseActivity(), View.OnClickListener {
    private var rl_followsytem: RelativeLayout? = null
    private var rl_simplified_chinese: RelativeLayout? = null
    private var rl_traditional_chinese: RelativeLayout? = null
    private var rl_english: RelativeLayout? = null
    private var iv_english: ImageView? = null
    private var iv_followsystem: ImageView? = null
    private var iv_simplified_chinese: ImageView? = null
    private var iv_traditional_chinese: ImageView? = null
    private var savedLanguageType = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_language)
        setTitle(R.string.setting_language_title)
        initViews()
    }

    private fun initViews() {
        rl_followsytem = findViewById(R.id.rl_followsytem)
        rl_simplified_chinese = findViewById(R.id.rl_simplified_chinese)
        rl_traditional_chinese = findViewById(R.id.rl_traditional_chinese)
        rl_english = findViewById(R.id.rl_english)
        iv_followsystem = findViewById(R.id.iv_followsystem)
        iv_english = findViewById(R.id.iv_english)
        iv_simplified_chinese = findViewById(R.id.iv_simplified_chinese)
        iv_traditional_chinese = findViewById(R.id.iv_traditional_chinese)
        rl_followsytem?.setOnClickListener(this)
        rl_simplified_chinese?.setOnClickListener(this)
        rl_traditional_chinese?.setOnClickListener(this)
        rl_english?.setOnClickListener(this)
        savedLanguageType = MultiLanguageUtil.getInstance()?.languageType!!
        if (savedLanguageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            setFollowSytemVisible()
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            setTraditionalVisible()
        } else if (savedLanguageType == LanguageType.LANGUAGE_EN) {
            setEnglishVisible()
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            setSimplifiedVisible()
        } else {
            setSimplifiedVisible()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        var selectedLanguage = 0
        when (id) {
            R.id.rl_followsytem -> {
                setFollowSytemVisible()
                selectedLanguage = LanguageType.LANGUAGE_FOLLOW_SYSTEM
            }
            R.id.rl_simplified_chinese -> {
                setSimplifiedVisible()
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_SIMPLIFIED
            }
            R.id.rl_traditional_chinese -> {
                setTraditionalVisible()
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_TRADITIONAL
            }
            R.id.rl_english -> {
                setEnglishVisible()
                selectedLanguage = LanguageType.LANGUAGE_EN
            }
        }
        MultiLanguageUtil.getInstance()?.updateLanguage(selectedLanguage)
        val intent = Intent(this@SetLanguageActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        //        if (selectedLanguage == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
////            System.exit(0);
//        }
    }

    private fun setSimplifiedVisible() {
        iv_followsystem!!.visibility = View.GONE
        iv_english!!.visibility = View.GONE
        iv_simplified_chinese!!.visibility = View.VISIBLE
        iv_traditional_chinese!!.visibility = View.GONE
    }

    private fun setEnglishVisible() {
        iv_followsystem!!.visibility = View.GONE
        iv_english!!.visibility = View.VISIBLE
        iv_simplified_chinese!!.visibility = View.GONE
        iv_traditional_chinese!!.visibility = View.GONE
    }

    private fun setTraditionalVisible() {
        iv_followsystem!!.visibility = View.GONE
        iv_english!!.visibility = View.GONE
        iv_simplified_chinese!!.visibility = View.GONE
        iv_traditional_chinese!!.visibility = View.VISIBLE
    }

    private fun setFollowSytemVisible() {
        iv_followsystem!!.visibility = View.VISIBLE
        iv_english!!.visibility = View.GONE
        iv_simplified_chinese!!.visibility = View.GONE
        iv_traditional_chinese!!.visibility = View.GONE
    }
}