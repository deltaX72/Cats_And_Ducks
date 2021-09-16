package com.litil.catsandducks.domain.di.modules

import dagger.Module

@Module(includes = [
    BindModule::class,
    NetworkModule::class
])
interface AppModule {

}