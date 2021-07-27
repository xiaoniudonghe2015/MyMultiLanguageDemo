package com.example.mymultilanguagedemo.kotlinmodule.lib

import android.content.Context
import android.content.SharedPreferences

/**
 * CommSharedUtils
 * 通用的SharedPreferences
 */
class CommSharedUtil private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences
    fun putInt(key: String?, value: Int) {
        val edit = sharedPreferences.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getInt(key: String?): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun putString(key: String?, value: String?) {
        val edit = sharedPreferences.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun getString(key: String?): String? {
        return sharedPreferences.getString(key, null)
    }

    fun putBoolean(key: String?, value: Boolean) {
        val edit = sharedPreferences.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return sharedPreferences.getInt(key, defValue)
    }

    fun remove(key: String?) {
        val edit = sharedPreferences.edit()
        edit.remove(key)
        edit.apply()
    }

    companion object {
        private const val SHARED_PATH = "app_info"
        private var helper: CommSharedUtil? = null
        fun getInstance(context: Context): CommSharedUtil? {
            if (helper == null) {
                helper = CommSharedUtil(context)
            }
            return helper
        }
    }

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE)
    }
}