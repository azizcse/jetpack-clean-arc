package com.tsl.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 2:37 PM
 */
@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        private var _mApplication = BaseApp()
        fun getApp(): BaseApp {
            return _mApplication
        }

        fun getContext(): Context {
            return _mApplication.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        _mApplication = this
    }
}