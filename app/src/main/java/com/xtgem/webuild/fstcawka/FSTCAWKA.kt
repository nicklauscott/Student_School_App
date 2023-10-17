package com.xtgem.webuild.fstcawka

import android.app.Application
import com.xtgem.webuild.fstcawka.models.constants.PreferenceRepository

class FSTCAWKA: Application() {
    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
        PreferenceRepository.initialize(this)
    }
}