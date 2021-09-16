package com.litil.catsandducks.domain.di.components

import com.litil.catsandducks.domain.di.modules.AppModule
import com.litil.catsandducks.presentation.activities.MainActivity
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)
}