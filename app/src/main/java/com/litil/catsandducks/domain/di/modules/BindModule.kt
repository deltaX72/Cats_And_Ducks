package com.litil.catsandducks.domain.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.CatsImagesRepository
import com.litil.catsandducks.data.repositories.CatsImagesRepositoryImpl
import com.litil.catsandducks.data.repositories.DucksImagesRepository
import com.litil.catsandducks.data.repositories.DucksImagesRepositoryImpl
import com.litil.catsandducks.presentation.fragments.AfterShiftFragment
import com.litil.catsandducks.presentation.fragments.BeforeShiftFragment
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import kotlin.reflect.KClass


@Module
interface BindModule {

    @Binds
    fun bindCatsImagesRepository(repository: CatsImagesRepositoryImpl): CatsImagesRepository

    @Binds
    fun bindDucksImagesRepository(repository: DucksImagesRepositoryImpl): DucksImagesRepository

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModel.Factory): ViewModelProvider.Factory

//    @FragmentScope
//    @ContributesAndroidInjector
//    fun provideBeforeShiftFragment(): BeforeShiftFragment
//
//    @FragmentScope
//    @ContributesAndroidInjector
//    fun provideAfterShiftFragment(): AfterShiftFragment
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope