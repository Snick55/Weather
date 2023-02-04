package com.snick.weather.core

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {

    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int,arg: Any): String

    class Base(private val context: Context): ResourceManager{

        override fun getString(id: Int): String {
            return context.getString(id)
        }

        override fun getString(id: Int, arg: Any): String {
           return context.getString(id,arg)
        }
    }

}