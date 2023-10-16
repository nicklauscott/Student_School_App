package com.xtgem.webuild.fstcawka.models.constants

import androidx.lifecycle.LiveData

class CustomLiveData<T>: LiveData<T>(){
    fun updateValue(newValue: T) {
        value = newValue
    }
}