package com.grupo3.smi_mobile_client_android

import android.app.Application
import com.grupo3.smi_mobile_client_android.di.AppContainer

class SmiApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}
