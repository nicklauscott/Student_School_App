package com.xtgem.webuild.fstcawka

import android.app.Application

class FSTCAWKA: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }
}