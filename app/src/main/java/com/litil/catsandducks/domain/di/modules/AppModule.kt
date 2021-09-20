package com.litil.catsandducks.domain.di.modules

import android.app.Application
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        BindModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
class AppModule {


}