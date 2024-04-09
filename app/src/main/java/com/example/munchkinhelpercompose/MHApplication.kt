package com.example.munchkinhelpercompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MHApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppStore.init(this)
    }
}