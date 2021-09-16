package com.litil

import android.app.Application
import android.content.Context
import com.litil.catsandducks.domain.di.components.AppComponent
import com.litil.catsandducks.domain.di.components.DaggerAppComponent

class MainApplication: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }