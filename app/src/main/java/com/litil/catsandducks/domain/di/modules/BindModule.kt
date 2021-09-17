package com.litil.catsandducks.domain.di.modules

import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.CatsImagesRepository
import com.litil.catsandducks.data.repositories.CatsImagesRepositoryImpl
import com.litil.catsandducks.data.repositories.DucksImagesRepositoryImpl
import com.litil.catsandducks.data.repositories.DucksImagesRepository
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun bindCatsImagesRepository(repository: CatsImagesRepositoryImpl): CatsImagesRepository

    @Binds
    fun bindDucksImagesRepository(repository: DucksImagesRepositoryImpl): DucksImagesRepository

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModel.Factory): ViewModelProvider.Factory
}