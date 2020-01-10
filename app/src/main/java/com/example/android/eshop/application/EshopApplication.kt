package com.example.android.eshop.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class EshopApplication : Application() {
    companion object {
        lateinit var instance: EshopApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}
