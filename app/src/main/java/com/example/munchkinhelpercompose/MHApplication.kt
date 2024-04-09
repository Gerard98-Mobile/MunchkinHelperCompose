package com.example.munchkinhelpercompose

import android.app.Application

class MHApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AppStore.init(this)
    }
}