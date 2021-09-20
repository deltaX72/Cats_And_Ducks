package com.litil.catsandducks.domain.di.components

import android.content.Context
import com.litil.catsandducks.domain.di.modules.AppModule
import com.litil.catsandducks.presentation.activities.MainActivity
import com.litil.catsandducks.presentation.fragments.AfterShiftFragment
import com.litil.catsandducks.presentation.fragments.BeforeShiftFragment
import com.litil.catsandducks.presentation.fragments.FavouritesFragment
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: MainViewModel)
    fun inject(fragment: BeforeShiftFragment)
    fun inject(fragment: AfterShiftFragment)
    fun inject(fragment: FavouritesFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}