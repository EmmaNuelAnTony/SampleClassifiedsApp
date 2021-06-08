package com.emmanuel.sampleclassifiedsapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


class PreferenceServices private constructor() {
    companion object {
        private const val PREFS_NAME = "SharedPref"

        @SuppressLint("StaticFieldLeak")
        val instance = PreferenceServices()

        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null


        fun init(context: Context) {
            mContext = context
        }
    }



    val prefs: SharedPreferences
        get() = mContext!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)


    fun clearPreference() {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

}